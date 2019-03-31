package com.alpdurmaz.logic.token;

import com.alpdurmaz.logic.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public String saveToken(int userId) {

        return tokenRepository.saveToken(userId);
    }

    public String getToken(User user) {

        return tokenRepository.getToken(user);
    }

    public void searchToken(String inputToken) {
        tokenRepository.searchToken(inputToken);
    }
}
