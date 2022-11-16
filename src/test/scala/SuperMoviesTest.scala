import com.movies.imdbmovies.{HandleMoviesData, SuperMovies}
import org.scalatest.funsuite.AnyFunSuite

import java.io.FileNotFoundException

class SuperMoviesTest extends AnyFunSuite{
  var superMovies = new SuperMovies("/Users/ramachandiran.devan/Documents/DMD/scala_file/IMDb_All_Genres_etf_clean1.csv", "Movie_Title,Year,Director,Actors,Rating,Runtime(Mins),Censor,Total_Gross,main_genre,side_genre")
  var result = superMovies.readMovies()
  test("verify that file read success and  got the result properly") {
    assert(result.nonEmpty)
    assert(result.head.equals("Kantara,2022,Rishab Shetty,\"Rishab Shetty, Sapthami Gowda, Kishore Kumar G., Achyuth Kumar\",9.3,148,UA,Gross Unkown,Action,\" Adventure,  Drama\""))
  }
  test("verify that output movies data written in the output path properly") {
    val writtenStatus = superMovies.writeMovieDataBasedOnLogic(s"/Users/ramachandiran.devan/Documents/DMD/scala_file/Movie_Data_By_Rating.csv",result ,HandleMoviesData.outputByHighestRating)
    assert(writtenStatus.equals("Data saved successfully"))
  }
  test("verify that throwing file not found exception when output location path is wrong") {
    assertThrows[FileNotFoundException] {
      superMovies.writeMovieDataBasedOnLogic(s"/Users/Documents/DMD/scala_file/Movie_Data_By_Rating.csv", result, HandleMoviesData.outputByHighestRating)
    }
  }



}
