package com.cinemafranchise.application;

import java.util.concurrent.ExecutionException;

import com.cinemafranchise.domain.movieshow.GetMovieShowQuery;
import com.cinemafranchise.domain.movieshow.MovieShow;
import com.cinemafranchise.shared.common.MovieTitle;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class MovieShowService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public ShowsDto getMovieTimesByTitle(@RequestBody MovieShowRequest request) throws ExecutionException, InterruptedException {
        MovieShow movieShow = queryGateway.query(
                new GetMovieShowQuery(MovieTitle.of(request.getTitle())),
                MovieShow.class).get();
        return new ShowsDto(movieShow.getShowTimes());
    }
}
