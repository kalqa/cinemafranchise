package com.cinemafranchise.domain.movieshow;

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
    public void should_expect_movie_show_price_changed_event_when_change_movie_show_price_command_was_passed() {
        MovieShow movieShow = MovieShowFixture.aMovieShow();
        fixture.givenNoPriorActivity()
                .when(new ChangeMovieShowPriceCommand(movieShow.getMovieShowId(), movieShow.getPrice()))
                .expectEvents(new MovieShowPriceChangedEvent(movieShow.getMovieShowId(), movieShow.getPrice()));
    }

    @Test
    public void should_expect_movie_show_time_changed_event_when_change_movie_show_time_command_was_passed() {
        MovieShow movieShow = MovieShowFixture.aMovieShow();
        fixture.givenNoPriorActivity()
                .when(new ChangeMovieShowTimeCommand(movieShow.getMovieShowId(), movieShow.getShowTime()))
                .expectEvents(new MovieShowTimeChangedEvent(movieShow.getMovieShowId(), movieShow.getShowTime()));
    }

    @Test
    public void should_change_show_time() {
        MovieShow movieShow = MovieShowFixture.aMovieShow();
        ShowTime givenShowTime = new ShowTime(ZonedDateTime.of(2010, 9, 21, 23, 0, 0, 0, ZoneId.systemDefault()));
        fixture.given(new CreateMovieShowCommand(movieShow.getMovieShowId(), movieShow.getMovieId(), movieShow.getPrice(), movieShow.getShowTime()))
                .when(new ChangeMovieShowTimeCommand(movieShow.getMovieShowId(), givenShowTime))
                .expectEvents(new MovieShowTimeChangedEvent(movieShow.getMovieShowId(), givenShowTime));
    }
}
