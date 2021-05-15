package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieTitle;
import lombok.Data;

@Data
public class GetMovieShowQuery {

    private final MovieTitle movieTitle;
}
