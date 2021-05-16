package com.cinemafranchise.security.login;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;

public interface SampleUser {

    default User sampleUser(String userName, String password) {
        return new User(userName, password, Collections.emptyList());
    }
}
