package com.cinemafranchise.domain.movie;

import java.util.List;

import com.cinemafranchise.shared.common.MovieStars;
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
    private String title;
    private MovieImdbId movieImdbId;
    private List<MovieStars> movieStars;

    protected Movie() {
    }

    @CommandHandler
    public Movie(CreateMovieCommand cmd) {
        AggregateLifecycle.apply(new MovieCreatedEvent(
                cmd.getTitle(),
                cmd.getMovieImdbId(),
                cmd.getMovieStars()));
    }

    @CommandHandler
    public void rateMovie(RateMovieCommand cmd) {
        AggregateLifecycle.apply(new MovieRatedEvent(cmd.getTitle(), cmd.getStars()));
    }

    @EventSourcingHandler
    public void on(MovieCreatedEvent event) {
        this.title = event.getMovieTitle();
        this.movieStars = event.getMovieStars();
    }

    @EventSourcingHandler
    public void on(MovieRatedEvent event) {
        this.title = event.getTitle();
        this.movieStars = event.getMovieRating();
    }
}
