package com.cinemafranchise.shared.common.dto;

import java.util.Set;

import lombok.Value;

@Value
public class ShowsDto {

    Set<ShowDto> shows;
}
