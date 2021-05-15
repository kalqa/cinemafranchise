package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieStars;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class MovieFixture {

    public static Movie aMovie() {
        return new Movie("", new MovieImdbId("id"), emptyList());
    }

    public static Movie aMovie(MovieStars stars) {
        return new Movie("", new MovieImdbId("id"), singletonList(stars));
    }
}
