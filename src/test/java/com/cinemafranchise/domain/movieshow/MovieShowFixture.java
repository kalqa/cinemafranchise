package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.cinemafranchise.shared.common.MovieId;

public class MovieShowFixture {

    public static MovieShow aMovieShow() {
        return new MovieShow(MovieShowId.newOne(), MovieId.newOne(), new Price(BigDecimal.ONE), new ShowTime(ZonedDateTime.now()));
    }
}
