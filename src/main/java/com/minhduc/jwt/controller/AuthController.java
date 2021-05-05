package com.minhduc.jwt.controller;

import com.minhduc.jwt.io.repository.RoleRepository;
import com.minhduc.jwt.service.UserService;
import com.minhduc.jwt.shared.dto.UserDto;
import com.minhduc.jwt.ui.model.request.UserDetailsRequestModel;
import com.minhduc.jwt.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private UserService userService;
    private RoleRepository roleRepository;

    public AuthController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        UserRest returnValue = new UserRest();

        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(userDetailsRequestModel, UserDto.class);

        userDto.getRole().add("ROLE_USER");

        UserDto createdUser = userService.createUser(userDto);
        returnValue = mapper.map(createdUser, UserRest.class);
        return returnValue;
    }

}
