package com.alpdurmaz.presentation.restservice.controller;

import com.alpdurmaz.logic.user.User;
import com.alpdurmaz.logic.user.UserService;
import com.alpdurmaz.logic.security.jwtsecurity.JwtGenerator;
import com.alpdurmaz.presentation.restservice.models.jwtmodels.JwtUser;
import com.alpdurmaz.presentation.web.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginRestController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private UserService userService;

    public LoginRestController(JwtGenerator jwtGenerator, UserService userService){
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @PostMapping("/gettoken")
    public String getToken(@RequestBody final JwtUser jwtUser){

        logger.info("sadasdasdada" + jwtUser.getEmail());

        User user = userService.findByEmail(jwtUser.getEmail());

        if(user != null && userService.validatePassword(jwtUser.getPassword(), user.getPassword())){
            return jwtGenerator.generate(jwtUser);
        }

        return "Invalid User!";
    }
}
