package com.cinemafranchise.application.controller;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class MovieShowRequest {

    String title;
    BigDecimal price;
    ZonedDateTime showTime;
}
