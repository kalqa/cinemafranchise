package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieId;
import com.cinemafranchise.shared.common.MovieTitle;
import lombok.Value;

@Value
public class MovieShowCreatedEvent {

    MovieTitle movieTitle;
    MovieId movieId;
    Shows shows;
}
