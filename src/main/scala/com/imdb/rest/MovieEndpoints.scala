package com.imdb.rest


import sttp.tapir.generic.auto._
import io.circe.{Json => CirceJson}
import io.circe.parser.{parse => circeParse}
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
import io.circe.syntax.EncoderOps
import sttp.tapir._
import sttp.capabilities.Streams
import sttp.tapir.typelevel.ParamConcat._
import sttp.tapir.typelevel.ParamsAsArgs._

object MovieEndpoints {

  val moviesFilterEndpoint: Endpoint[Json, Unit, Stream[IO, List[Film]], Any] =
    endpoint.get
      .in("movies")
      .in(jsonBody[Json])
      .out(streamBody(new Streams[Any])(Schema.derived[List[Film]], CodecFormat.Json())) // yet to be completed


  val filmsServerEndpoint:  ServerEndpoint[Json, Unit, Stream[IO, List[Film]], Any, IO]  =
    moviesFilterEndpoint.serverLogic { filmFilterParams =>

      // Process the request and return a list of films or an exception
      filmParamDecoder.decodeJson(circeParse(filmFilterParams.toString).getOrElse(CirceJson.Null)) match {
        case Right(filmFilter) => IO.pure(Right(MovieStore.getMoviesUnderParams(filmFilter)))
        case _ => IO.raiseError( InvalidInputException("Invalid input"))
      }
    }

}
