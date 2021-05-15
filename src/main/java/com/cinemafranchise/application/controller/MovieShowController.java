package com.cinemafranchise.application.controller;

import java.util.concurrent.ExecutionException;

import com.cinemafranchise.application.dto.ShowsDto;
import com.cinemafranchise.application.service.MovieShowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/movieshow")
public class MovieShowController {

    private final MovieShowService service;

    @GetMapping
    public ShowsDto getMovieTimesByTitle(@RequestBody MovieShowRequest movieShowRequest) throws ExecutionException, InterruptedException {
        return service.findMovieTimesByTitle(movieShowRequest);
    }

    @PostMapping
    public void postMovieShow(@RequestBody MovieShowRequest movieShowRequest) {
        service.saveShow(movieShowRequest);
    }

    @PostMapping("/showTimeAndPrice")
    public void updateShowTimeAndPrice(@RequestBody MovieShowRequest movieShowRequest) {
        service.updateShowTimeAndPrice(movieShowRequest);
    }
}
