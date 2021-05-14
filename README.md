# Core domain model
### MovieShow - representing single movie show at certain time
- MovieShowId movieShowId ex. 1231
- Instant showTime ex. 2019-12-21T12:00Z
- BigDecimal price ex. 20
- String movieTitle ex. Fast&Furious 2
### Movie - representing single movie in the catalogue
- MovieId movieId
- MovieTitle title
- MovieImdbId imdbId
- MovieRating rating
# Decided to use Event Sourcing with Axon
### pros:
- no db migration needed
- history of choices easy Undo/Redo
- simple logic to manage state
- dedicated EventStore with great integration with Spring Boot and Java - AxonServer https://axoniq.io/product-overview/axon-server
- I can use writeModel simultaneously as readModel - which simplifies queries and writes.
### cons:
- Not CQRS but CQS used.
- not that much experience with event sourcing