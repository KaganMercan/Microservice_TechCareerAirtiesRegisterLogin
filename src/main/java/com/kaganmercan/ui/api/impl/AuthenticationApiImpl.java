package com.kaganmercan.ui.api.impl;

import com.kaganmercan.business.dto.UserDto;
import com.kaganmercan.business.services.IAuthenticationService;
import com.kaganmercan.business.services.IUserServices;
import com.kaganmercan.security.jwt.JwtProviderImpl;
import com.kaganmercan.ui.api.IAuthenticationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

//lombok
@RequiredArgsConstructor
// In API layer, for example we POST our Http Request and get Http response.
//  So we are using ResponseEntity in this layer.
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationApiImpl implements IAuthenticationApi {

    // Injection
    private final IAuthenticationService authenticationService;
    private final IUserServices userServices;

    private final AuthenticationManager authenticationManager;
    private final JwtProviderImpl jwtUtils;


    //REGISTER
    // Localhost
    // http://localhost:1111/api/authentication/register
    // EC2
    // http://ec2-54-211-143-21.compute-1.amazonaws.com:1111/api/authentication/register
    @Override
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        // kullanıcı adımız unique olmalıdır.
        if (userServices.findUsername(userDto.getUsername()).isPresent()) {
            //aynı kullanıcı varsa conflict oluşturalım
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userServices.createUser(userDto), HttpStatus.CREATED);
    }

    //LOGIN
    // Localhost
    // http://localhost:1111/api/authentication/login
    // EC2
    // http://ec2-54-211-143-21.compute-1.amazonaws.com:1111/api/authentication/login
    @Override
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        //UserPrincipal userLoginDetails= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println(userLoginDetails);
        return new ResponseEntity<>(authenticationService.loginReturnJwt(userDto), HttpStatus.OK);
    }

    //LIST
    // Localhost
    // http://localhost:1111/api/authentication/list
    // EC2
    // http://ec2-54-211-143-21.compute-1.amazonaws.com:1111/api/authentication/list
    @Override
    @GetMapping("list")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(userServices.getAllUser(), HttpStatus.OK);
    }
}
