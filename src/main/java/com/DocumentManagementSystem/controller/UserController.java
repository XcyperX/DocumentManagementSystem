package com.DocumentManagementSystem.controller;

import com.DocumentManagementSystem.dto.UserDto;
import com.DocumentManagementSystem.model.User;
import com.DocumentManagementSystem.security.SecurityUtils;
import com.DocumentManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final MapperFacade mapper;
    private final UserService userService;

    @GetMapping("/users/registration")
    private String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/users/registration")
    private String createUser(@ModelAttribute("user") UserDto userDto) {
        try {
            userService.save(mapper.map(userDto, User.class));
        } catch (Exception e) {
            return "redirect:/users/registration?error";
        }
        return "redirect:/users";
    }

    @GetMapping("/login")
    private String getLoginPage() {
        return "login";
    }

    @GetMapping("/users")
    private String getAvailableUsersPage(Model model) {
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.isNull(userFromContext)) {
            return "redirect:/login";
        }
        List<User> userList = userService.getAll();
        model.addAttribute("users", mapper.mapAsList(userList, UserDto.class));
        return "users";
    }

    @GetMapping("/users/{id}")
    private String getUsersByIdPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", mapper.map(userService.getById(id), UserDto.class));
        return "viewUser";
    }

    @DeleteMapping("/users/{id}")
    private void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @GetMapping("/users/edit/{id}")
    private String getEditUserPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", mapper.map(userService.getById(id), UserDto.class));
        return "editUser";
    }

    @PostMapping("/users/edit/{id}")
    private String createUser(@PathVariable("id") Long id, @ModelAttribute("user") UserDto userDto) {
        userDto.setId(id);
        userService.update(mapper.map(userDto, User.class));
        return "redirect:/users";
    }
}
