package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
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

    @CommandHandler
    public MovieShow(ChangeMovieShowPriceCommand cmd) {
        AggregateLifecycle.apply(new MovieShowPriceChangedEvent(cmd.getMovieShowId(), cmd.getPrice()));
    }

    @CommandHandler
    public MovieShow(ChangeMovieShowTimeCommand cmd) {
        AggregateLifecycle.apply(new MovieShowTimeChangedEvent(cmd.getMovieShowId(), cmd.getShowTime()));
    }

    @EventSourcingHandler
    public void on(MovieShowPriceChangedEvent event) {
        this.movieShowId = event.getMovieShowId();
        this.price = event.getPrice();
    }

    @EventSourcingHandler
    public void on(MovieShowTimeChangedEvent event) {
        this.movieShowId = event.getMovieShowId();
        this.showTime = event.getShowTime();
    }
}
