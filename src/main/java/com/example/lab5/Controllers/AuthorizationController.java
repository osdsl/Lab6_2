package com.example.lab5.Controllers;

import com.example.lab5.Services.UserService;
import com.example.lab5.Dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthorizationController{
    @Autowired
    UserService userService;

    //
    // Registration handler
    //
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Validated(UserDto.registration.class) UserDto userdto, BindingResult bindingResult, Model model) {
        if (!userdto.getPassword().equals(userdto.getPasswordConfirm())){
            bindingResult.addError(new ObjectError("passwordError", "Пароли не совпадают!"));
        }
        if (userService.existsByUsername(userdto.getUsername())){
            bindingResult.addError(new ObjectError("usernameError", "Это имя уже занято!"));
        }
        if (bindingResult.hasErrors()){

            model.addAttribute("bindingResult", bindingResult.getGlobalErrors());
            return "registration";
        }
        userService.saveUser(userdto);
        return "redirect:login";
    }

    //
    // Login handler
    //
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Validated(UserDto.login.class) UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        if (userService.loadUserByUsername(userDto.getUsername()).getAuthorities().contains("ROLE_ADMIN")){
            return "redirect:/api/admin";
        }
        return "redirect:/api";
    }
    // Login form with error
    @PostMapping("/login-error")
    public String loginError(@ModelAttribute @Validated UserDto userDto, Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
