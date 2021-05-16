package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
        ZonedDateTime givenShowTime = ZonedDateTime.now();
        BigDecimal givenPrice = BigDecimal.ONE;

        fixture.given(new MovieShowCreatedEvent(movieShow.getTitle(), movieShow.getMovieId(), givenShowTime, givenPrice))
                .when(new ChangeMovieShowPriceAndTimeCommand(movieShow.getTitle(), givenShowTime, givenPrice))
                .expectEvents(new MovieShowPriceAndTimeChangedEvent(movieShow.getTitle(), givenShowTime, givenPrice));
    }

    @Test
    public void should_change_show_time_and_price() {
        MovieShow movieShow = MovieShowFixture.aMovieShow();
        ZonedDateTime givenShowTime = ZonedDateTime.of(2010, 9, 21, 23, 0, 0, 0, ZoneId.systemDefault());
        BigDecimal givenPrice = BigDecimal.ONE;
        BigDecimal expectedPrice = BigDecimal.TEN;

        fixture.given(new MovieShowCreatedEvent(movieShow.getTitle(), movieShow.getMovieId(), givenShowTime, givenPrice))
                .when(new ChangeMovieShowPriceAndTimeCommand(movieShow.getTitle(), givenShowTime, expectedPrice))
                .expectEvents(new MovieShowPriceAndTimeChangedEvent(movieShow.getTitle(), givenShowTime, expectedPrice));
    }

    @Test
    public void should_change_only_show_time() {
        MovieShow movieShow = MovieShowFixture.aMovieShow();
        ZonedDateTime givenShowTime = ZonedDateTime.of(2011, 9, 21, 23, 0, 0, 0, ZoneId.systemDefault());
        ZonedDateTime expectedShowTime = ZonedDateTime.of(2010, 9, 21, 23, 0, 0, 0, ZoneId.systemDefault());
        BigDecimal givenPrice = BigDecimal.ONE;

        fixture.given(new MovieShowCreatedEvent(movieShow.getTitle(), movieShow.getMovieId(), givenShowTime, givenPrice))
                .when(new ChangeMovieShowPriceAndTimeCommand(movieShow.getTitle(), expectedShowTime, givenPrice))
                .expectEvents(new MovieShowPriceAndTimeChangedEvent(movieShow.getTitle(), expectedShowTime, givenPrice));
    }

    @Test
    public void should_change_only_price() {
        MovieShow movieShow = MovieShowFixture.aMovieShow();
        ZonedDateTime givenShowTime = ZonedDateTime.of(2010, 9, 21, 23, 0, 0, 0, ZoneId.systemDefault());
        BigDecimal givenPrice = BigDecimal.ONE;
        BigDecimal expectedPrice = BigDecimal.TEN;

        fixture.given(new MovieShowCreatedEvent(movieShow.getTitle(), movieShow.getMovieId(), givenShowTime, givenPrice))
                .when(new ChangeMovieShowPriceAndTimeCommand(movieShow.getTitle(), givenShowTime, expectedPrice))
                .expectEvents(new MovieShowPriceAndTimeChangedEvent(movieShow.getTitle(), givenShowTime, expectedPrice));
    }
}
