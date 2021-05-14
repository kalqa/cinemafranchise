package com.cinemafranchise.domain.movie;

import lombok.Value;

@Value
public class MovieRating {

    MovieStars stars;

    public static MovieRating notRatedYet() {
        return of(MovieStars.NOT_RATED);
    }

    public static MovieRating of(MovieStars movieStars) {
        return new MovieRating(movieStars);
    }
}
