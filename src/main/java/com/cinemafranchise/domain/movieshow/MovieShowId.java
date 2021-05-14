package com.cinemafranchise.domain.movieshow;

import java.util.UUID;

import lombok.Value;

@Value
class MovieShowId {

    UUID id;

    public static MovieShowId newOne() {
        return new MovieShowId(UUID.randomUUID());
    }
}
