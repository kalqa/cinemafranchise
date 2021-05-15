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
        ZonedDateTime now = ZonedDateTime.now();
        BigDecimal one = BigDecimal.ONE;

        fixture.given(new MovieShowCreatedEvent(movieShow.getTitle(), movieShow.getMovieId(), now, one))
                .when(new ChangeMovieShowPriceAndTimeCommand(movieShow.getTitle(), now, one))
                .expectEvents(new MovieShowPriceAndTimeChangedEvent(movieShow.getTitle(), now, one));
    }

    @Test
    public void should_change_show_time_and_price() {
        MovieShow movieShow = MovieShowFixture.aMovieShow();
        ZonedDateTime givenShowTime = ZonedDateTime.of(2010, 9, 21, 23, 0, 0, 0, ZoneId.systemDefault());
        BigDecimal one = BigDecimal.ONE;
        BigDecimal ten = BigDecimal.TEN;

        fixture.given(new MovieShowCreatedEvent(movieShow.getTitle(), movieShow.getMovieId(), givenShowTime, one))
                .when(new ChangeMovieShowPriceAndTimeCommand(movieShow.getTitle(), givenShowTime, ten))
                .expectEvents(new MovieShowPriceAndTimeChangedEvent(movieShow.getTitle(), givenShowTime, ten));
    }
}
