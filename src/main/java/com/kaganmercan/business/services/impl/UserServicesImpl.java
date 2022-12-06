package com.kaganmercan.business.services.impl;

import com.kaganmercan.bean.ModelMapperBean;
import com.kaganmercan.bean.PasswordEncoderBean;
import com.kaganmercan.business.dto.UserDto;
import com.kaganmercan.business.services.IUserServices;
import com.kaganmercan.data.entity.UserEntity;
import com.kaganmercan.data.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Lombok
@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
//asıl işi yapan yer
public class UserServicesImpl implements IUserServices {

    // Injection
    private final IUserRepository repository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;

    // Model Mapper -> Entity <-> Dto Conversions
    @Override
    public UserDto EntityToDto(UserEntity userEntity) {
        return modelMapperBean.modelMapperMethod().map(userEntity,UserDto.class);
    }

    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        return modelMapperBean.modelMapperMethod().map(userDto,UserEntity.class);
    }

    // CREATE (Register)
    @Override
    @PostMapping("/save/product")
    public UserDto createUser(UserDto userDto) {
        if(userDto!=null){
            // We mask our password in here
            userDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(userDto.getPassword()));
            // Mapping our Dto
            UserEntity userEntity=DtoToEntity(userDto);
            // And save to repository
            repository.save(userEntity);
        }
        return userDto;
    }

    // LIST
    @Override
    @GetMapping("/list/product")
    public List<UserDto> getAllUser() {
       Iterable<UserEntity>  userEntityList= repository.findAll();
        List<UserDto> dtoList=new ArrayList<>();
        for (UserEntity temp:  userEntityList) {
            UserDto userDto=EntityToDto(temp);
            dtoList.add(userDto);
        }
        return dtoList;
    }

    // FIND
    @Override
    public Optional<UserEntity> findUsername(String username) {
        return repository.findByUsername(username);
    }
}
