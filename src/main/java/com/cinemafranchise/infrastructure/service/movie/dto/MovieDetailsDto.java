package com.cinemafranchise.infrastructure.service.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailsDto {

    @JsonProperty("Title")
    String name;
    @JsonProperty("Year")
    String year;

    public static MovieDetailsDto empty() {
        return new MovieDetailsDto("empty", "empty");
    }
}
