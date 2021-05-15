package com.cinemafranchise.application.service;

import com.cinemafranchise.domain.movieshow.MovieShow;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Repository<MovieShow> movieShowRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(MovieShow.class)
                .eventStore(eventStore)
                .build();
    }
}