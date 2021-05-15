package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieId;
import lombok.Data;

@Data
public class GetMovieShowByShowsQuery {

    private final MovieId movieId;
}
