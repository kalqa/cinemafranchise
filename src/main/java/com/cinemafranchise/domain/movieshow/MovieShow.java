package com.cinemafranchise.domain.movieshow;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.cinemafranchise.application.dto.ShowDto;
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
    private String title;
    private MovieId movieId;
    private Shows shows;

    protected MovieShow() {
    }

    @CommandHandler
    public MovieShow(CreateMovieShowCommand cmd) {
        AggregateLifecycle.apply(new MovieShowCreatedEvent(cmd.getMovieTitle(), cmd.getMovieId(), cmd.getTime(), cmd.getValue()));
    }

    @CommandHandler
    public void changePriceAndTime(ChangeMovieShowPriceAndTimeCommand cmd) {
        AggregateLifecycle.apply(new MovieShowPriceAndTimeChangedEvent(cmd.getMovieTitle(), cmd.getTime(), cmd.getValue()));
    }

    public Set<ShowDto> getShowTimes() {
        return shows.getShows().stream().map(show -> new ShowDto(show.getShowTime(), show.getPrice()))
                .collect(Collectors.toSet());
    }

    @EventSourcingHandler
    public void on(MovieShowPriceAndTimeChangedEvent event) {
        this.title = event.getMovieTitle();
        this.shows = new Shows(Collections.singleton(new Show(event.getTime(), event.getValue())));
    }

    @EventSourcingHandler
    public void on(MovieShowCreatedEvent event) {
        this.title = event.getMovieTitle();
        this.shows = new Shows(Collections.singleton(new Show(event.getTime(), event.getValue())));
        this.movieId = event.getMovieId();
    }
}
