package com.cinemafranchise.domain.movieshow;

import lombok.Value;

@Value
public class MovieShowPriceChangedEvent {

    MovieShowId movieShowId;
    Price price;
}
