
# Deploying IMDB APIs using Tapir and FS2

Simple application to Deploy IMDB APIs using Tapir and FS2 by employing Functional programming


## List of APIs:

GET /movies
To get complete list of movies

GET /movies
Payload: 
{
    name: "Name of film required",  // optional
    year: "Year of film required"   // optional
}
To get the full info of the movie requested

## Application running and deployment

To deploy application follow:

```sh
sbt package
```

extract from `sparkassessment_2.13-0.1.jar`

`/target/scala-2.13/`
