package com.imdb.rest


import sttp.tapir.generic.auto._
import io.circe.{Json=> CirceJson}
import io.circe.parser.{parse=> circeParse}
import cats.effect.IO
import com.imdb.Domain.{Film, FilmFilterParams}
import com.imdb.domain.Exceptions.InvalidInputException
import fs2.Stream
import com.imdb.domain.FilmImplicits._
import com.imdb.store.MovieStore
import sttp.tapir.CodecFormat.Json
import sttp.tapir.{Endpoint, endpoint}
import sttp.tapir.json.circe._
import sttp.tapir.server.ServerEndpoint
import io.circe.generic.auto._
import sttp.tapir._
import sttp.capabilities.
object MovieEndpoints {

  val moviesFilterEndpoint: Endpoint[Unit, Json, Unit, Stream[IO, Either[Throwable, List[Film]]], Any] =
    endpoint.get
      .in("movies")
      .in(jsonBody[Json])
      .out(streamBody[IO, Either[Throwable, List[Film]]]()) // yet to be completed


  //  ServerEndpoint[Unit, Unit, Stream[IO, Film], Any, IO]
  val filmsServerEndpoint:  ServerEndpoint.Full[Unit, Unit, Json, Unit, Stream[IO, Either[Throwable, List[Film]]], Any, IO]  =
    moviesFilterEndpoint.serverLogic { filmFilterParams =>

      // Process the request and return a list of films or an exception
      filmParamDecoder.decodeJson(circeParse(filmFilterParams.toString).getOrElse(CirceJson.Null)) match {
        case Right(filmFilter) => IO.pure(Right(MovieStore.getMoviesUnderParams(filmFilter)))
        case _ => IO.raiseError( InvalidInputException("Invalid input"))
      }
    }

}
