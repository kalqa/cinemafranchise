package com.cinemafranchise.domain.movie;

import java.util.List;

import com.cinemafranchise.shared.common.MovieStars;
import lombok.Value;

@Value
public class MovieRatedEvent {

    String title;
    List<MovieStars> movieRating;
}
