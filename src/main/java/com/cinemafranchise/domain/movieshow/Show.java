package com.cinemafranchise.domain.movieshow;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import lombok.Value;

@Value
class Show {

    ShowTime showTime;
    Price price;

    public Show(ZonedDateTime time, BigDecimal price) {
        this.showTime = new ShowTime(time);
        this.price = new Price(price);
    }

    public ZonedDateTime getShowTime() {
        return showTime.getTime();
    }

    public BigDecimal getPrice() {
        return price.getValue();
    }
}
