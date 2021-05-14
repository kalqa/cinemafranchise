package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieId;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class MovieShow {

    @AggregateIdentifier
    MovieShowId movieShowId;
    MovieId movieId;
    Price price;
    ShowTime showTime;

    protected MovieShow() {
    }
}
