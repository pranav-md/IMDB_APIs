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

  def getMoviesUnderParamsAsStream(params: FilmFilterParams): Stream[IO, List[Film]] = {
    Stream.emit {
      getMoviesUnderParams(params)
    }
  }

  def getMoviesUnderParams(params: FilmFilterParams): List[Film] = {
    val filteredFilms = getAllMoviesData().foldLeft(List.empty: List[Film]) {
      (allFilms, film) =>
        params match {
          case FilmFilterParams(Some(name), Some(year))
            if film.original_title.equals(name) && film.year.equals(year) => allFilms :+ film
          case FilmFilterParams(None, Some(year))
            if film.year.equals(year) => allFilms :+ film
          case FilmFilterParams(Some(name), None)
            if film.original_title.equals(name) => allFilms :+ film
          case FilmFilterParams(_, _) => allFilms
        }
    }

    filteredFilms
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
