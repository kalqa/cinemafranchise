package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;
import com.cinemafranchise.shared.common.MovieTitle;
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
public class Movie {

    @AggregateIdentifier
    private MovieId movieId;
    private MovieImdbId movieImdbId;
    private MovieTitle movieTitle;
    private MovieRating movieRating;

    protected Movie() {
    }

    @CommandHandler
    public Movie(CreateMovieCommand cmd) {
        AggregateLifecycle.apply(new MovieCreatedEvent(cmd.getMovieId(), cmd.getMovieImdbId(), cmd.getMovieTitle(), cmd.getMovieRating()));
    }

    @CommandHandler
    public Movie(RateMovieCommand cmd) {
        AggregateLifecycle.apply(new MovieRatedEvent(cmd.getMovieId(), cmd.getMovieRate()));
    }

    @EventSourcingHandler
    public void on(MovieCreatedEvent event) {
        this.movieId = event.getMovieId();
        this.movieRating = MovieRating.notRatedYet();
    }

    @EventSourcingHandler
    public void on(MovieRatedEvent event) {
        this.movieId = event.getMovieId();
        this.movieRating = event.getMovieRating();
    }
}
