package com.alpdurmaz.logic.security.websecurity;

import com.alpdurmaz.logic.user.User;
import com.alpdurmaz.logic.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class WebAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = userService.findByEmail(authentication.getName());

        if(user == null){
            throw new RuntimeException("User Not Found");
        }

        if(userService.validatePassword(authentication.getCredentials().toString(), user.getPassword())){
            return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), Collections.EMPTY_LIST);
        }

        else{
            throw new RuntimeException("Invalid Password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
