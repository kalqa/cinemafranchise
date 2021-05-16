package com.cinemafranchise.application.controller;

import com.cinemafranchise.application.service.MovieService;
import com.cinemafranchise.infrastructure.service.movie.dto.MovieDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService service;

    @PostMapping
    public void saveMovie(@RequestBody MovieRequest movieRequest) {
        service.saveMovie(movieRequest);
    }

    @PostMapping("/{title:[a-zA-Z :&+-]*}/rating")
    public void saveMovieRating(@PathVariable String title,
                                @RequestBody RateMovieRequest rateMovieRequest) {
        service.saveMovieRating(title, rateMovieRequest);
    }

    @GetMapping("/{title:[a-zA-Z :&+-]*}/details")
    public MovieDetailsDto fetchMovieDetails(@PathVariable String title) {
        return service.fetchMovieDetails(title);
    }
}
