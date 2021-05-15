package com.cinemafranchise.application.controller;

import java.util.concurrent.ExecutionException;

import com.cinemafranchise.application.service.MovieShowService;
import com.cinemafranchise.shared.common.dto.ShowsDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/movieshow")
public class MovieShowController {

    private final MovieShowService service;

    @GetMapping("/{title:[a-zA-Z &+-]*}")
    public ShowsDto getMovieTimesByTitle(@PathVariable String title) throws ExecutionException, InterruptedException {
        return service.findMovieTimesByTitle(title);
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
