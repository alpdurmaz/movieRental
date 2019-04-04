package com.alpdurmaz.logic.security.jwtsecurity;

import com.alpdurmaz.presentation.restservice.models.jwtmodels.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter(){
        super("/rest/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request
            , HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String header = request.getHeader("Authorisation");

        if(header == null || !header.startsWith("Token ")){
            throw new RuntimeException("JWT Token is Missing");
        }

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);

        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain chain
            ,Authentication authentication) throws IOException, ServletException{

        super.successfulAuthentication(request, response, chain, authentication);
        chain.doFilter(request, response);
    }
}
