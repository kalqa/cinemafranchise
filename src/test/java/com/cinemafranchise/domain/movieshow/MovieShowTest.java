package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieShowTest {

    private FixtureConfiguration<MovieShow> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(MovieShow.class);
    }

    @Test
    public void should_handle_movie_show_price_and_time_changed_event_when_change_movie_show_price_and_time_command_was_called() {
        MovieShow movieShow = MovieShowFixture.aMovieShow();

        fixture.givenNoPriorActivity()
                .when(new ChangeMovieShowPriceAndTimeCommand(movieShow.getMovieTitle(), movieShow.getShows()))
                .expectEvents(new MovieShowPriceAndTimeChangedEvent(movieShow.getMovieTitle(), movieShow.getShows()));
    }

    @Test
    public void should_change_show_time_and_price() {
        MovieShow movieShow = MovieShowFixture.aMovieShow();
        ShowTime givenShowTime = new ShowTime(ZonedDateTime.of(2010, 9, 21, 23, 0, 0, 0, ZoneId.systemDefault()));
        MovieShow givenShow = MovieShowFixture.aMovieShow(new Shows(Collections.singletonMap(givenShowTime, new Price(BigDecimal.TEN))));

        Shows showTimePriceMap = new Shows(Collections.singletonMap(new ShowTime(ZonedDateTime.now()), new Price(BigDecimal.ONE)));
        fixture.given(new CreateMovieShowCommand(movieShow.getMovieTitle(), movieShow.getMovieId(), showTimePriceMap))
                .when(new ChangeMovieShowPriceAndTimeCommand(movieShow.getMovieTitle(), givenShow.getShows()))
                .expectEvents(new MovieShowPriceAndTimeChangedEvent(movieShow.getMovieTitle(), givenShow.getShows()));
    }
}
