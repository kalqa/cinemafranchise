package com.cinemafranchise.domain.movieshow;

import java.util.UUID;

import lombok.Value;

@Value
public class MovieShowId {

    UUID id;

    public static MovieShowId newOne() {
        return new MovieShowId(UUID.randomUUID());
    }

    public static MovieShowId newOne(UUID id) {
        return new MovieShowId(id);
    }
}
