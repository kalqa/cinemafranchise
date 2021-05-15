package com.cinemafranchise.domain.movieshow;

import lombok.Value;

@Value
public class MovieShowPriceAndTimeChangedEvent {

    MovieShowId movieShowId;
    Shows shows;
}
