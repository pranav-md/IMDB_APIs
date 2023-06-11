package com.imdb.domain

object Exceptions {

  case class FilmNotFoundException(message: String) extends Exception(message)

  case class InvalidInputException(message: String) extends Exception(message)

}
