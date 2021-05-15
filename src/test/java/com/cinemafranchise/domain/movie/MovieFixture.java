package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;
import com.cinemafranchise.shared.common.MovieTitle;

public class MovieFixture {

    public static Movie aMovie() {
        return new Movie(MovieId.newOne(), new MovieImdbId("id"), new MovieTitle(""), MovieRating.notRatedYet());
    }

    public static Movie aMovie(MovieRating rating) {
        return new Movie(MovieId.newOne(), new MovieImdbId("id"), new MovieTitle(""), rating);
    }
}
