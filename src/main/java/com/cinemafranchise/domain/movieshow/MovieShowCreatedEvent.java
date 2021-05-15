package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieId;
import lombok.Value;

@Value
public class MovieShowCreatedEvent {

    MovieShowId movieShowId;
    MovieId movieId;
    Shows shows;
}
