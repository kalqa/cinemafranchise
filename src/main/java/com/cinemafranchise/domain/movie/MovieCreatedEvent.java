package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;
import lombok.Value;

@Value
public class MovieCreatedEvent {

    MovieId movieId;
}
