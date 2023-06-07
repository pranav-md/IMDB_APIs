package com.imdb

object Domain {

  case class Film(imdb_title_id: String,
                  original_title: String,
                  year: Int,
                  genre: String,
                  duration: Int,
                  director: String,
                  writer: String,
                  production_company: String,
                  actors: String,
                  description: String,
                  avg_vote: Double,
                  votes: Long)


  case class FilmFilterParams(name: Option[String] = None, year: Option[Int] = None)
}
