package com.cinemafranchise.shared.common;

import java.util.UUID;

import lombok.Value;

@Value
public class MovieId {

    UUID id;

    public static MovieId newOne() {
        return new MovieId(UUID.randomUUID());
    }

    public static MovieId newOne(String id) {
        return new MovieId(UUID.fromString(id));
    }
}
