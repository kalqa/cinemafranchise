package com.cinemafranchise.application.controller;

import com.cinemafranchise.shared.common.MovieStars;
import lombok.Data;

@Data
public class MovieRequest {

    String title;
    MovieStars movieStars;
}
