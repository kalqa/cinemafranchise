package com.cinemafranchise.domain.movieshow;

import java.util.Set;
import java.util.stream.Collectors;

import com.cinemafranchise.application.ShowDto;
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

    public Set<ShowDto> getShowTimes() {
        return shows.getShows().stream().map(show -> new ShowDto(show.getShowTime(), show.getPrice()))
                .collect(Collectors.toSet());
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
