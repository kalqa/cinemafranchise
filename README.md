## Assumptions:
1. No database needed - getting data directly from Axon eventStore
2. Aggregates (MovieShow, Movie) identifier is [String] movieTitle so its easy to look for aggregate using only movie title which is given
3. Decided to use Java instead of Kotlin due to smaller experience with DDD using Kotlin
4. I leave space for handling exceptions in app by now throwing IllegalStateException -> I know better practice is to inform user why something didnt work. 
5. Also HTTP Statuses in REST controllers are not finished.


## Core domain model
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

### 
for AxonServer UI go to: http://localhost:8024/


### pros:
- no db migration needed
- history of choices easy Undo/Redo
- simple logic to manage state
- dedicated EventStore with great integration with Spring Boot and Java - AxonServer https://axoniq.io/product-overview/axon-server
- I can use writeModel simultaneously as readModel - which simplifies queries and writes.
### cons:
- Not CQRS but CQS used.
- not that much experience with event sourcing

# Swagger URL
### 
 http://localhost:8080/swagger-ui
 
# HTTP client
## used RestTemplate library for exchange to imdb API
#### To configure api-key, go to application.properties and insert 
movie.http.client.config.imdb.apikey=[your_key]

For Example: 
movie.http.client.config.imdb.apikey=812371029

### Http client integration tests 
I decided to use WireMock server for integration test

### REST Controllers

#### I decided to match movie by given title path due to assumption with getting data directly from eventStore instead of Database (no migration needed)


## MovieController

#### Creating Movie
POST http://localhost:8080/movie/
Content-Type: application/json

{
  "title": "The Fast and the Furious: Tokyo Drift",
  "imdbId": "tt0463985",
  "movieStars": "ONE_STAR"
}

#### Give Rating to a Movie
POST http://localhost:8080/movie/The Fast and the Furious: Tokyo Drift/rating
Content-Type: application/json

{
  "movieStars": "TWO_STARS"
}

#### Fetch Movie Details by title
GET http://localhost:8080/movie/The Fast and the Furious: Tokyo Drift/details
Content-Type: application/json

## MovieShowController


#### Fetch Movie show times by title
GET http://localhost:8080/movieshow/The Fast and the Furious: Tokyo Drift
Content-Type: application/json


#### Update show time and price for movieshow
POST http://localhost:8080/movieshow
Content-Type: application/json

{
  "title": "The Fast and the Furious: Tokyo Drift"",
  "price": 12,
  "showTime": "2021-05-15T21:46:25.38+01:00"
}

