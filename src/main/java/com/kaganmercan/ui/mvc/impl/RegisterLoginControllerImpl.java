package com.kaganmercan.ui.mvc.impl;

import com.kaganmercan.bean.AuthenticationServiceBean;
import com.kaganmercan.bean.ModelMapperBean;
import com.kaganmercan.business.dto.UserDto;
import com.kaganmercan.business.services.IUserServices;
import com.kaganmercan.data.entity.UserEntity;
import com.kaganmercan.data.repository.IUserRepository;
import com.kaganmercan.security.UserDetailsServiceCustom;
import com.kaganmercan.security.UserPrincipal;
import com.kaganmercan.ui.mvc.IRegisterLoginController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author kaganmercan
 */

//lombok
@RequiredArgsConstructor
@Log4j2

// Daily and blog controller will be added.
@Controller
public class RegisterLoginControllerImpl implements IRegisterLoginController {
    private final IUserRepository repository;
    private final UserDetailsServiceCustom userDetailsService;
    private final ModelMapperBean modelMapperBean;

    @Override
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("key_register", new UserDto());
        return "register";
    }
    // Register
    // http://localhost:1111/register
    @Override
    @PostMapping("/register")
    public String validationPostRegisterPage(@Valid @ModelAttribute("key_register") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("ERROR: " + bindingResult);
            return "register";
        }
        model.addAttribute("register_success", "User register successful.");
        log.info("Success " + userDto);
        UserEntity userEntity = modelMapperBean.modelMapperMethod().map(userDto, UserEntity.class);
        try{
            if(userEntity != null){
                repository.save(userEntity);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "register";
    }

    @Override
    @GetMapping("/login")
    public String loginPage(UserDto userDto, Model model) {
        model.addAttribute("key_login", new UserDto());
        return "login";
    }

    @Override
    @GetMapping("/home")
    public String homePage(Model model) {
        return "home";
    }
}
