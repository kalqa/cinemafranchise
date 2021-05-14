package com.cinemafranchise.domain.movie;

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
                .when(new CreateMovieCommand(movie.getMovieId(), movie))
                .expectEvents(new MovieCreatedEvent(movie.getMovieId(), movie));
    }

    @Test
    public void should_expect_movie_rated_event_when_rate_movie_command_was_passed() {
        Movie movie = MovieFixture.aMovie();
        fixture.givenNoPriorActivity()
                .when(new RateMovieCommand(movie.getMovieId(), movie.getMovieRating()))
                .expectEvents(new MovieRatedEvent(movie.getMovieId(), movie.getMovieRating()));
    }

    @Test
    public void should_change_movie_rating_from_five_stars_to_one_star() {
        Movie givenMovie = MovieFixture.aMovie(MovieRating.of(MovieStars.FIVE_STARS));

        fixture.given(new CreateMovieCommand(givenMovie.getMovieId(), givenMovie))
                .when(new RateMovieCommand(givenMovie.getMovieId(), MovieRating.of(MovieStars.ONE_STAR)))
                .expectEvents(new MovieRatedEvent(givenMovie.getMovieId(), MovieRating.of(MovieStars.ONE_STAR)));
    }

    @Test
    public void should_change_movie_rating_from_not_yet_rated_to_three_stars() {
        Movie givenMovie = MovieFixture.aMovie();

        fixture.given(new CreateMovieCommand(givenMovie.getMovieId(), givenMovie))
                .when(new RateMovieCommand(givenMovie.getMovieId(), MovieRating.of(MovieStars.THREE_STARS)))
                .expectEvents(new MovieRatedEvent(givenMovie.getMovieId(), MovieRating.of(MovieStars.THREE_STARS)));
    }
}
