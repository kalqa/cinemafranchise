package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;

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
public class MovieShow {

    @AggregateIdentifier
    private MovieTitle movieTitle;
    private MovieId movieId;
    private Shows shows;

    public static Shows createSingleShow(ZonedDateTime time, BigDecimal value) {
        return new Shows(Collections.singletonMap(new ShowTime(time), new Price(value)));
    }

    protected MovieShow() {
    }

    @CommandHandler
    public MovieShow(CreateMovieShowCommand cmd) {
        AggregateLifecycle.apply(new MovieShowCreatedEvent(cmd.getMovieTitle(), cmd.getMovieId(), cmd.getShows()));
    }

    @CommandHandler
    public MovieShow(ChangeMovieShowPriceAndTimeCommand cmd) {
        AggregateLifecycle.apply(new MovieShowPriceAndTimeChangedEvent(cmd.getMovieTitle(), cmd.getShows()));
    }

    @EventSourcingHandler
    public void on(MovieShowPriceAndTimeChangedEvent event) {
        this.movieTitle = event.getMovieShowId();
        this.shows = event.getShows();
    }

    @EventSourcingHandler
    public void on(MovieShowCreatedEvent event) {
        this.movieTitle = event.getMovieTitle();
        this.shows = event.getShows();
        this.movieId = event.getMovieId();
    }
}
