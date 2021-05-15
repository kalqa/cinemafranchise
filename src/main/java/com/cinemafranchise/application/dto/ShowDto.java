package com.cinemafranchise.application.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import lombok.Value;

@Value
public class ShowDto {

    ZonedDateTime time;
    BigDecimal price;
}
