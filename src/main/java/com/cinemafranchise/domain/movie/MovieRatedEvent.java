package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;
import lombok.Value;

@Value
public class MovieRatedEvent {

    MovieId movieId;
    MovieRating movieRating;
}
