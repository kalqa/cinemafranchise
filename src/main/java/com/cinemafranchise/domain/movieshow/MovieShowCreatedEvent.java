package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.cinemafranchise.shared.common.MovieId;
import lombok.Value;

@Value
public class MovieShowCreatedEvent {

    String movieTitle;
    MovieId movieId;
    ZonedDateTime time;
    BigDecimal value;
}
