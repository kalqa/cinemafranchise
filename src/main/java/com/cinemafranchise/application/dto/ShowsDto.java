package com.cinemafranchise.application.dto;

import java.util.Set;

import lombok.Value;

@Value
public class ShowsDto {

    Set<ShowDto> shows;
}
