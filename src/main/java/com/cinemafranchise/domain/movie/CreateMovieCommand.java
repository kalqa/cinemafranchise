package com.cinemafranchise.domain.movie;

import java.util.List;

import com.cinemafranchise.shared.common.MovieStars;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateMovieCommand {

    @TargetAggregateIdentifier
    String title;
    String movieImdbId;
    List<MovieStars> movieStars;
}
