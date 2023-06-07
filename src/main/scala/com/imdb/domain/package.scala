package com.imdb

import com.imdb.Domain.{Film, FilmFilterParams}
import io.circe.Decoder
import io.circe.generic.semiauto._


package object domain {


  object FilmImplicits {

    implicit val filmDecoder = deriveDecoder[Film]
    implicit val filmEncoder = deriveEncoder[Film]

    implicit val filmParamDecoder = deriveDecoder[FilmFilterParams]
    implicit val filmParamEncoder = deriveEncoder[FilmFilterParams]
  }
}
