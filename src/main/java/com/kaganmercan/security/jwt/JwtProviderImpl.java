package com.kaganmercan.security.jwt;

import com.kaganmercan.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


//spring tarafında instance oluşturmak için
@Component
public class JwtProviderImpl implements IJwtProvider {

    // Token header variables
    private static final String JWT_TOKEN_PREFIX = "Bearer";
    private static final String JWT_HEADER_STRING = "Authorization";

    // Token lifecycle
    @Value("${authentication.jwt.expiration-ms}")
    private Long JWT_EXPIRATION_MS;

    // Private and public key
    private PublicKey jwtPublicKey;
    private PrivateKey jwtPrivateKey;

    public JwtProviderImpl(
            @Value("${authentication.jwt.public-key}") String jwtPublicKeyStr,
            @Value("${authentication.jwt.private-key}") String jwtPrivateKeyStr) throws InvalidKeySpecException {
        try {
            //Java security RSA kullanıdğımızı belirtmemiz için kullanıyoruz
            KeyFactory keyFactory = getKeyFactory();

            //gizli ve ozel anahtarlar Base64 anahtarlama olduğunda açmamız gerekiyor.
            Base64.Decoder decoder = Base64.getDecoder();

            //Spring security için anahtar kodlayıcı genel ve ozel anahtar için
            //private key kodlamak
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decoder.decode(jwtPrivateKeyStr.getBytes()));

            //public key kodlamak
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decoder.decode(jwtPublicKeyStr.getBytes()));

            jwtPrivateKey = keyFactory.generatePrivate(privateKeySpec);
            jwtPublicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            throw new RuntimeException("Invalid key specification ", e);
        }
    }


    // 1.YÖNTEM
    //key factory method: Java security RSA kullanıdğımızı belirtmemiz için kullanıyoruz
    @Override
    public KeyFactory getKeyFactory() {
        try {
            return KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unknow key generation algorithm ", e);
        }
    }

    //1.YÖNTEM generateToken
    // (JWT token oluşturmak) öncelikle sistemde kullanıcı kimliği doğrulanmışssa buradan devam edilir.
    // USerPrincipal: kullanıcı email,şifre,roller vardır.
    @Override
    public String generateToken(UserPrincipal authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        return Jwts.builder().setSubject(authentication.getUsername())
                //kullanıcı ID almak
                .claim("userId", authentication.getId())
                //kullanıc rol
                .claim("roles", authorities)
                //token başlama ve bitiş süresi
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                //jwt imzalamak için signWith farklı olanları kullanabiliriz en iyisi  SignatureAlgorithm.RS512
                .signWith(jwtPrivateKey, SignatureAlgorithm.RS512)
                .compact();
    }

    // resolveToken
    /*/The HttpServletRequest object can be used to retrieve incoming HTTP request
        headers and form data. The HttpServletResponse object can be used to set
            the HTTP response headers (e.g., content-type) and the response message body./*/
    @Override
    public String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(JWT_HEADER_STRING);
        //token null ise return null döndürelim
        if (bearerToken != null && bearerToken.startsWith(JWT_TOKEN_PREFIX))
            return bearerToken.substring(7);
        return null;
    }

    // getAuthentication
    @Override
    public Authentication getAuthentication(HttpServletRequest httpServletRequest) {
        String token = resolveToken(httpServletRequest);
        if (token == null)
            return null;
        // For access all JWT information wwe use claims.
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtPublicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);
        List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        UserDetails userDetails = new UserPrincipal(userId, username, null);
        Authentication kimlikDogrulama = username != null ? new UsernamePasswordAuthenticationToken(userDetails, null, authorities) : null;
        return kimlikDogrulama;
    }

    // Validation for control expiration date of JWT
    @Override
    public boolean isValidateToken(HttpServletRequest httpServletRequest) {
        String token = resolveToken(httpServletRequest);
        if (token == null)
            return false;
        // In here we use claims again to access all parsed JWT information
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtPublicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        // Then we get expiration information inside the Claims data.
        if (claims.getExpiration().before(new Date()))
            return false;
        return true;
    }
}
