package com.cinemafranchise.domain.movie;

import com.cinemafranchise.shared.common.MovieId;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieTest {

    private FixtureConfiguration<Movie> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Movie.class);
    }

    @Test
    public void should_expect_movie_created_event_when_create_movie_command_was_passed() {
        MovieId movieId = MovieId.newOne();
        fixture.givenNoPriorActivity()
                .when(new CreateMovieCommand(movieId))
                .expectEvents(new MovieCreatedEvent(movieId));
    }

    @Test
    public void should_not_expect_movie_created_event_when_create_movie_command_was_not_passed() {

    }

    @Test
    public void should_expect_movie_rated_event_when_rate_movie_command_was_passed() {

    }

    @Test
    public void should_not_expect_movie_rated_event_when_rate_movie_command_was_not_passed() {

    }
}
