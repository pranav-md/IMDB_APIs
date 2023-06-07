
import com.imdb.Domain.{Film, FilmFilterParams}
import io.circe.Json
import io.circe.parser.parse
import fs2._

import scala.io.Source



def getFilmByFilter(filmSearchParams: FilmFilterParams): Stream[Either[Throwable, List[Film]], Unit] = {
  Stream.eval {
    try {
      val filteredFilms = films.filter(_.director == director)
      Right(filteredFilms)
    } catch {
      case e: Throwable => Left(e)
    }
  }
}

def getAllMoviesData(): Json ={

  val jsonString: String = Source.fromResource("movies.json").mkString
  val json = parse(jsonString).getOrElse(Json.Null)

  json
}

println(getAllMoviesData())