package com.alpdurmaz.logic.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserLoginRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
