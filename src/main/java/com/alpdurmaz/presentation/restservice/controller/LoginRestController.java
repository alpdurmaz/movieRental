package com.alpdurmaz.presentation.restservice.controller;

import com.alpdurmaz.logic.token.TokenService;
import com.alpdurmaz.logic.user.User;
import com.alpdurmaz.logic.user.UserService;
import com.alpdurmaz.presentation.restservice.model.LoginInfoJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/get-token")
    public String getToken(@RequestBody LoginInfoJson loginInfoJson){

        User user = userService.findByEmail(loginInfoJson.getEmail());

        String token = "";

        if(userService.validatePassword(loginInfoJson.getPassword(),user.getPassword())){
                token = tokenService.getToken(user);
        }

        return token;
    }

    @PostMapping("/api/register-token")
    public String registerToken(@RequestBody LoginInfoJson loginInfoJson){

        User user = userService.findByEmail(loginInfoJson.getEmail());

        if(userService.validatePassword(user.getPassword(), loginInfoJson.getPassword())){
            return tokenService.saveToken(user.getId());
        }

        return "Invalid User";
    }
}
