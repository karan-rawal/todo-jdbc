package com.karan.todo.controller;

import com.karan.todo.exceptions.UserException;
import com.karan.todo.model.User;
import com.karan.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid User user, BindingResult result) throws UserException {
        if (result.hasErrors()) {
            List<String> errors = getFormattedErrorsList(result);
            throw new UserException(UserException.USER_VALIDATION, UserException.CODE_USER_VALIDATION, errors);
        }
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    private List<String> getFormattedErrorsList(BindingResult result) {
        return result.getFieldErrors().stream().map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
