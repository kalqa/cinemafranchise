package com.cinemafranchise.shared.common;

import lombok.Value;

@Value
public class MovieTitle {

    String title;

    public static MovieTitle of(String title) {
        return new MovieTitle(title);
    }
}
