package com.kaganmercan.ui.api;

import com.kaganmercan.business.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationApi {
    // REGISTER
    ResponseEntity<?> register(@RequestBody UserDto userDto);

    // LOGIN
    ResponseEntity<?> login(@RequestBody UserDto userDto);

    // LIST
    ResponseEntity<?> list();
}
