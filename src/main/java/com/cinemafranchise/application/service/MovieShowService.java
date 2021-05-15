package com.cinemafranchise.application.service;

import java.util.concurrent.ExecutionException;

import com.cinemafranchise.application.controller.MovieShowRequest;
import com.cinemafranchise.domain.movieshow.ChangeMovieShowPriceAndTimeCommand;
import com.cinemafranchise.domain.movieshow.CreateMovieShowCommand;
import com.cinemafranchise.domain.movieshow.GetMovieShowQuery;
import com.cinemafranchise.domain.movieshow.MovieShow;
import com.cinemafranchise.shared.common.MovieId;
import com.cinemafranchise.shared.common.dto.ShowsDto;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieShowService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public ShowsDto findMovieTimesByTitle(String title) throws ExecutionException, InterruptedException {
        MovieShow movieShow = queryGateway.query(
                new GetMovieShowQuery(title),
                MovieShow.class).get();
        return new ShowsDto(movieShow.getShowTimes());
    }

    public void updateShowTimeAndPrice(MovieShowRequest request) {
        commandGateway.send(new ChangeMovieShowPriceAndTimeCommand(request.getTitle(), request.getShowTime(), request.getPrice()));
    }

    public void saveShow(MovieShowRequest request) {
        commandGateway.send(new CreateMovieShowCommand(request.getTitle(), MovieId.newOne(), request.getShowTime(), request.getPrice()));
    }
}
