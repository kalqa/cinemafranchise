package com.cinemafranchise.domain.movieshow;

import java.util.Map;

import lombok.Value;

@Value
class Shows {

    Map<ShowTime, Price> shows;
}
