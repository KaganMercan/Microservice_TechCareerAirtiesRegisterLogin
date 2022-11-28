package com.kaganmercan.business.services;

import com.kaganmercan.business.dto.UserDto;
import com.kaganmercan.data.entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface IUserServices {

    // Model Mapper -> Entity <-> Dto Conversions
    public UserDto EntityToDto(UserEntity userEntity);
    public UserEntity DtoToEntity(UserDto userDto);

    // CREATE (Register)
    public UserDto createUser(UserDto userDto);

    // LIST
    public List<UserDto> getAllUser();

    // FIND
    public Optional<UserEntity> findUsername(String username);
}
