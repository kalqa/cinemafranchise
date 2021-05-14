package com.cinemafranchise.domain.movieshow;

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
        MovieShowId movieShowId = MovieShowId.newOne();
        Price price = new Price();
        fixture.givenNoPriorActivity()
                .when(new ChangeMovieShowPriceCommand(movieShowId, price))
                .expectEvents(new MovieShowPriceChangedEvent(movieShowId, price));
    }

    @Test
    public void should_expect_movie_show_time_changed_event_when_change_movie_show_time_command_was_passed() {
        MovieShowId movieShowId = MovieShowId.newOne();
        ShowTime showTime = new ShowTime();
        fixture.givenNoPriorActivity()
                .when(new ChangeMovieShowTimeCommand(movieShowId, showTime))
                .expectEvents(new MovieShowTimeChangedEvent(movieShowId, showTime));
    }
}
