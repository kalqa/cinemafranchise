package com.cinemafranchise.domain.movie;

import java.util.Collections;

import com.cinemafranchise.shared.common.MovieStars;
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
        Movie movie = MovieFixture.aMovie();
        fixture.givenNoPriorActivity()
                .when(new CreateMovieCommand(movie.getTitle(), "id", movie.getMovieStars()))
                .expectEvents(new MovieCreatedEvent(movie.getTitle(), "id", movie.getMovieStars()));
    }

    @Test
    public void should_expect_movie_rated_event_when_rate_movie_command_was_passed() {
        Movie movie = MovieFixture.aMovie();
        fixture.given(new MovieCreatedEvent(movie.getTitle(), "id", movie.getMovieStars()))
                .when(new RateMovieCommand(movie.getTitle(), movie.getMovieStars()))
                .expectEvents(new MovieRatedEvent(movie.getTitle(), movie.getMovieStars()));
    }

    @Test
    public void should_change_movie_rating_from_five_stars_to_one_star() {
        Movie givenMovie = MovieFixture.aMovie(MovieStars.FIVE_STARS);

        fixture.given(new MovieCreatedEvent(givenMovie.getTitle(), "id", givenMovie.getMovieStars()))
                .when(new RateMovieCommand(givenMovie.getTitle(), Collections.singletonList(MovieStars.ONE_STAR)))
                .expectEvents(new MovieRatedEvent(givenMovie.getTitle(), Collections.singletonList(MovieStars.ONE_STAR)));
    }

    @Test
    public void should_change_movie_rating_from_not_yet_rated_to_three_stars() {
        Movie givenMovie = MovieFixture.aMovie();

        fixture.given(new MovieCreatedEvent(givenMovie.getTitle(), "id", givenMovie.getMovieStars()))
                .when(new RateMovieCommand(givenMovie.getTitle(), Collections.singletonList(MovieStars.THREE_STARS)))
                .expectEvents(new MovieRatedEvent(givenMovie.getTitle(), Collections.singletonList((MovieStars.THREE_STARS))));
    }
}
