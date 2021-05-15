package com.cinemafranchise.domain.movieshow;

import com.cinemafranchise.shared.common.MovieTitle;
import lombok.Value;

@Value
public class MovieShowPriceAndTimeChangedEvent {

    MovieTitle movieShowId;
    Shows shows;
}
