package com.alpdurmaz.logic.token;

import com.alpdurmaz.logic.user.User;

public interface TokenRepository {
    String saveToken(int userId);
    String getToken(User user);
    String searchToken(String tokenInput);
}
