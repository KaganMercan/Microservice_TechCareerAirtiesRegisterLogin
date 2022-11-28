package com.kaganmercan.business.services;

import com.kaganmercan.business.dto.UserDto;

public interface IAuthenticationService {

    String loginReturnJwt(UserDto userDto);
}
