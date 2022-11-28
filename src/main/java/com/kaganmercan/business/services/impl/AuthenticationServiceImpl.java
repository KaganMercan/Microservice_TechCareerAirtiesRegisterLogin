package com.kaganmercan.business.services.impl;

import com.kaganmercan.business.dto.UserDto;
import com.kaganmercan.business.services.IAuthenticationService;
import com.kaganmercan.security.UserPrincipal;
import com.kaganmercan.security.jwt.IJwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationServiceImpl implements IAuthenticationService {

    // Injection
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IJwtProvider iJwtProvider;

    @Override
    public String loginReturnJwt(UserDto userDto) {
        // Authentication -> Give username and password to userDto
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        // UserPrincipal
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        // IJwtProvider
        return iJwtProvider.generateToken(userPrincipal);
    }
}