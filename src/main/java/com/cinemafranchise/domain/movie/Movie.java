package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Movie {

    @AggregateIdentifier
    MovieId movieId;
    MovieImdbId movieImdbId;
    MovieTitle movieTitle;
    MovieRating movieRating;

    protected Movie() {
    }

    @CommandHandler
    public Movie(CreateMovieCommand createMovieCommand) {
        AggregateLifecycle.apply(new MovieCreatedEvent(createMovieCommand.getMovieId()));
    }

    @EventSourcingHandler
    public void on(MovieCreatedEvent event) {
        this.movieId = event.getMovieId();
    }
}
