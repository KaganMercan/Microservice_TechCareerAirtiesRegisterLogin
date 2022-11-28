package com.kaganmercan.ui.mvc;

import com.kaganmercan.business.dto.UserDto;
import com.kaganmercan.security.UserPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaganmercan
 */
public interface IRegisterLoginController {


    public String registerPage(Model model);

    public String validationPostRegisterPage(UserDto userDto, BindingResult bindingResult, Model model);

    public String loginPage(UserDto userDto, Model model);

    public String homePage(Model model);
}
