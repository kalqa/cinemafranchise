package com.cinemafranchise.security.login;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public class NotForProductionUserRepository {

    public Optional<User> findByUsername(String username) {
        if (username.equals("test")) {
            return Optional.of(new User("test", "$2y$12$.jx571FDVWPc7tiYoG4vJ.TtBgwHjo6R487QkqlfegpdTDIUjDzwC", Collections.emptyList()));
        }
        return Optional.empty();
    }
}
