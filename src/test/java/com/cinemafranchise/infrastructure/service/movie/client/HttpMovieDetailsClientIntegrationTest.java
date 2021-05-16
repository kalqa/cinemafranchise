package com.cinemafranchise.infrastructure.service.movie.client;

import com.cinemafranchise.infrastructure.service.movie.MovieHttpClientTestConfig;
import com.cinemafranchise.infrastructure.service.movie.RemoteMovieDetailsClient;
import com.cinemafranchise.infrastructure.service.movie.dto.MovieDetailsDto;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.SocketUtils;
import wiremock.org.apache.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.BDDAssertions.then;

@ActiveProfiles("test")
class HttpMovieDetailsClientIntegrationTest {

    int port = SocketUtils.findAvailableTcpPort();

    WireMockServer wireMockServer;

    RemoteMovieDetailsClient remoteMovieDetailsClient = new MovieHttpClientTestConfig().remoteMovieTestClient("http://localhost", port, 1000, 1000, "key");

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        WireMock.configureFor(port);
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void should_return_movie_details() {
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .withQueryParam("apikey", equalTo("key"))
                .withQueryParam("i", equalTo("imdbId"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(withMovieDetails())));

        then(remoteMovieDetailsClient.getDetails("imdbId", "/"))
                .isEqualTo(aFastAndFuriousDto());
    }

    @Test
    void should_fail_with_connection_reset_by_peer() {
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.CONNECTION_RESET_BY_PEER)));

        then(remoteMovieDetailsClient.getDetails("imdbId", "/")).isEqualTo(emptyDto());
    }

    @Test
    void should_fail_with_empty_response() {
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.EMPTY_RESPONSE)));

        then(remoteMovieDetailsClient.getDetails("imdbId", "/")).isEqualTo(emptyDto());
    }

    @Test
    void should_fail_with_malformed() {
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.MALFORMED_RESPONSE_CHUNK)));

        then(remoteMovieDetailsClient.getDetails("imdbId", "/")).isEqualTo(emptyDto());
    }

    @Test
    void should_fail_with_random() {
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.RANDOM_DATA_THEN_CLOSE)));

        then(remoteMovieDetailsClient.getDetails("imdbId", "/")).isEqualTo(emptyDto());
    }

    @Test
    void should_return_empty_body_when_status_is_no_content() {
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_NO_CONTENT)
                        .withHeader("Content-Type", "application/json")
                        .withBody(emptyJsonBody())));

        then(remoteMovieDetailsClient.getDetails("imdbId", "/")).isEqualTo(emptyDto());
    }

    @Test
    void should_return_empty_body_when_status_is_ok() {
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(emptyJsonBody())));

        then(remoteMovieDetailsClient.getDetails("imdbId", "/")).isEqualTo(emptyDto());
    }

    @Test
    void should_return_response_not_found_status_exception_when_http_service_returning_not_found_status() {
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(HttpStatus.SC_NOT_FOUND))
        );

        BDDAssertions.thenThrownBy(() ->
                remoteMovieDetailsClient.getDetails("imdbId", "/"))
                .hasMessage("404 NOT_FOUND");
    }

    @Test
    void should_return_response_unauthorized_status_exception_when_http_service_returning_unauthorized_status() {
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(HttpStatus.SC_UNAUTHORIZED))
        );

        BDDAssertions.thenThrownBy(() ->
                remoteMovieDetailsClient.getDetails("imdbId", "/"))
                .hasMessage("401 UNAUTHORIZED");
    }

    @Test
    void should_return_empty_body_when_response_delay_is_1500_milis() {
        RemoteMovieDetailsClient remoteMovieDetailsClient = new MovieHttpClientTestConfig().remoteMovieTestClient("http://localhost", 5555, 1000, 1000, "key");
        WireMock.stubFor(any(urlPathEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(withMovieDetails())
                        .withFixedDelay(1500)));

        then(remoteMovieDetailsClient.getDetails("imdbId", "/")).isEqualTo(emptyDto());
    }

    private MovieDetailsDto emptyDto() {
        return MovieDetailsDto.empty();
    }

    private MovieDetailsDto aFastAndFuriousDto() {
        return new MovieDetailsDto("The Fast and the Furious", "2001");
    }

    private String emptyJsonBody() {
        return "";
    }

    private String withMovieDetails() {
        return "{  \n" +
                "   \"Title\":\"The Fast and the Furious\",\n" +
                "   \"Year\":\"2001\",\n" +
                "   \"Rated\":\"PG-13\",\n" +
                "   \"Released\":\"22 Jun 2001\",\n" +
                "   \"Runtime\":\"106 min\",\n" +
                "   \"Genre\":\"Action, Crime, Thriller\",\n" +
                "   \"Director\":\"Rob Cohen\",\n" +
                "   \"Writer\":\"Ken Li (magazine article \\\"Racer X\\\"), Gary Scott Thompson (screen story), Gary Scott Thompson (screenplay), Erik Bergquist (screenplay), David Ayer (screenplay)\",\n" +
                "   \"Actors\":\"Paul Walker, Vin Diesel, Michelle Rodriguez, Jordana Brewster\",\n" +
                "   \"Plot\":\"Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.\",\n" +
                "   \"Language\":\"English, Spanish\",\n" +
                "   \"Country\":\"USA, Germany\",\n" +
                "   \"Awards\":\"11 wins & 12 nominations.\",\n" +
                "   \"Poster\":\"https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg\",\n" +
                "   \"Ratings\":[  \n" +
                "      {  \n" +
                "         \"Source\":\"Internet Movie Database\",\n" +
                "         \"Value\":\"6.8/10\"\n" +
                "      },\n" +
                "      {  \n" +
                "         \"Source\":\"Rotten Tomatoes\",\n" +
                "         \"Value\":\"53%\"\n" +
                "      },\n" +
                "      {  \n" +
                "         \"Source\":\"Metacritic\",\n" +
                "         \"Value\":\"58/100\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"Metascore\":\"58\",\n" +
                "   \"imdbRating\":\"6.8\",\n" +
                "   \"imdbVotes\":\"322,264\",\n" +
                "   \"imdbID\":\"tt0232500\",\n" +
                "   \"Type\":\"movie\",\n" +
                "   \"DVD\":\"01 Jan 2002\",\n" +
                "   \"BoxOffice\":\"$142,542,950\",\n" +
                "   \"Production\":\"Universal Pictures\",\n" +
                "   \"Website\":\"http://www.thefastandthefurious.com\",\n" +
                "   \"Response\":\"True\"\n" +
                "}";
    }
}