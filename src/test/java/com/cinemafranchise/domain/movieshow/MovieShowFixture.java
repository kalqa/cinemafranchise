package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;

import com.cinemafranchise.shared.common.MovieId;
import com.cinemafranchise.shared.common.MovieTitle;

public class MovieShowFixture {

    public static MovieShow aMovieShow() {
        return aMovieShow(new Shows(Collections.singletonMap(new ShowTime(ZonedDateTime.now()), new Price(BigDecimal.ONE))));
    }

    public static MovieShow aMovieShow(Shows shows) {
        return new MovieShow(new MovieTitle("Fast Furious"), MovieId.newOne(), shows);
    }
}
