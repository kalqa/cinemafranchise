package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@AllArgsConstructor
@Getter
public class MovieShow {

    @AggregateIdentifier
    private MovieShowId movieShowId;
    private MovieId movieId;
    private Shows shows;

    protected MovieShow() {
    }

    @CommandHandler
    public MovieShow(CreateMovieShowCommand cmd) {
        AggregateLifecycle.apply(new MovieShowCreatedEvent(cmd.getMovieShowId(), cmd.getMovieId(), cmd.getShows()));
    }

    @CommandHandler
    public MovieShow(ChangeMovieShowPriceAndTimeCommand cmd) {
        AggregateLifecycle.apply(new MovieShowPriceAndTimeChangedEvent(cmd.getMovieShowId(), cmd.getShows()));
    }

    @EventSourcingHandler
    public void on(MovieShowPriceAndTimeChangedEvent event) {
        this.movieShowId = event.getMovieShowId();
        this.shows = event.getShows();
    }

    @EventSourcingHandler
    public void on(MovieShowCreatedEvent event) {
        this.movieShowId = event.getMovieShowId();
        this.shows = event.getShows();
        this.movieId = event.getMovieId();
    }
}
