package com.alpdurmaz.logic.security.jwtsecurity;

import com.alpdurmaz.presentation.restservice.models.jwtmodels.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "soto babi";

    public JwtUser validate(String token){

        JwtUser jwtUser = null;

        try{
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setEmail(body.getSubject());
            jwtUser.setPassword((String)body.get("password"));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e){
            System.out.println(e);
        }

        return jwtUser;
    }
}
