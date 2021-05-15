package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;

import com.cinemafranchise.shared.common.MovieId;

public class MovieShowFixture {

    public static MovieShow aMovieShow() {
        return aMovieShow(new Shows(Collections.singleton(new Show(ZonedDateTime.now(), BigDecimal.ONE))));
    }

    public static MovieShow aMovieShow(Shows shows) {
        return new MovieShow("Fast Furious", MovieId.newOne(), shows);
    }
}
