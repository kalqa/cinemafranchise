package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;

public class MovieFixture {

    public static Movie aMovie() {
        return new Movie(MovieId.newOne(), new MovieImdbId("id"), new MovieTitle(""), MovieRating.notRatedYet());
    }
}
