package com.alpdurmaz.presentation.restservice.jwtsecurity;

import com.alpdurmaz.presentation.restservice.models.jwtmodels.JwtAuthenticationToken;
import com.alpdurmaz.presentation.restservice.models.jwtmodels.JwtUser;
import com.alpdurmaz.presentation.restservice.models.jwtmodels.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username
            , UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;

        String token = jwtAuthenticationToken.getToken();

        JwtUser jwtUser = validator.validate(token);

        if(jwtUser == null){
            throw new RuntimeException("JWT Token is Incorrect");
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser.getRole());

        return new JwtUserDetails(jwtUser.getEmail(), jwtUser.getPassword(), token, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication){
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
