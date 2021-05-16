package com.cinemafranchise.infrastructure.service.movie;

import com.cinemafranchise.infrastructure.service.movie.dto.MovieDetailsDto;

public interface RemoteMovieDetailsClient {

    MovieDetailsDto getDetails(String id);
}
