package store

import com.imdb.Domain.{Film, FilmFilterParams}
import com.imdb.store.MovieStore.{getAllMoviesData, getMoviesUnderParams}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, EitherValues, GivenWhenThen}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import store.MovieStoreTest.allMovies

class MovieStoreTest extends AnyWordSpec with BeforeAndAfterAll with BeforeAndAfterEach with EitherValues with Matchers with GivenWhenThen {


  "Movie store object" should {
    "get all movies data" in {
      val moviedData = getAllMoviesData()
      moviedData.size shouldBe 100
    }

    "get all movie Mononoke-hime released on 1997" in {
      val moviedDataResult = getMoviesUnderParams(FilmFilterParams(Some("Mononoke-hime"), Some(1997)))
      moviedDataResult shouldBe allMovies
    }
  }
}

object MovieStoreTest {
  val allMovies = List(Film("tt0119698", "Mononoke-hime", 1997, "Animation, Adventure, Fantasy", 134, """Hayao Miyazaki""",
    "Hayao Miyazaki, Neil Gaiman", "DENTSU Music And Entertainment",
    """Billy Crudup, Billy Bob Thornton, Minnie Driver, John DiMaggio, Claire Danes, John DeMita, Jada Pinkett Smith, Gillian Anderson, Keith David, Corey Burton, Tara Strong, Julia Fletcher, Debi Derryberry, Alex Fernandez, Jack Fletcher""".stripMargin, """On a journey to find the cure for a Tatarigami's curse, Ashitaka finds himself in the middle of a war between the forest gods and Tatara, a mining colony. In this quest he also meets San, the Mononoke Hime.""".stripMargin, 8.4, 331045))
}