package com.imdb.store

import cats.effect.IO
import com.imdb.Domain.{Film, FilmFilterParams}
import com.imdb.domain.Exceptions.FilmNotFoundException
import com.imdb.domain.FilmImplicits._
import io.circe.Json
import io.circe.parser._

import scala.io.Source
import fs2.Stream

object MovieStore {

  def getMoviesUnderParams(params: FilmFilterParams): Stream[IO, Either[Throwable, List[Film]]] = {
    Stream.emit {
        val filteredFilms = getAllMoviesData().foldLeft(List.empty: List[Film]){
          (allFilms, film) => params match {
            case FilmFilterParams(Some(name), Some(year))
              if film.original_title.equals(name) && film.year.equals(year) => allFilms :+ film
            case FilmFilterParams(_, Some(year))
              if film.year.equals(year) => allFilms :+ film
            case FilmFilterParams(Some(name), _)
              if film.original_title.equals(name) => allFilms :+ film
            case FilmFilterParams(_, _) => allFilms
          }
        }

        filteredFilms match {
          case films if films.isEmpty => Left(FilmNotFoundException(s"Films with given parameters are not found"))
          case films: List[Film] => Right(films)
        }
    }
  }

  def streamFilms(): Stream[IO, Film] = {
    Stream.emits(getAllMoviesData())
  }

  def getAllMoviesData(): List[Film] = {
    val jsonString: String = Source.fromResource("movies.json").mkString
    val json = parse(jsonString).getOrElse(Json.Null)

    json.as[List[Film]].getOrElse(List.empty[Film])
  }

}
