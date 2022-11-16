import com.movies.imdbmovies.{HandleMoviesData, SuperMovies}
import org.scalatest.funsuite.AnyFunSuite

class HandleMoviesDataTest extends AnyFunSuite{
  var superMovies = new SuperMovies("/Users/u1126039/Downloads/IMDb_All_Genres_etf_clean1.csv", "Movie_Title,Year,Director,Actors,Rating,Runtime(Mins),Censor,Total_Gross,main_genre,side_genre");
  var listOfMovies = superMovies.readMovies();
  test("verify that outputByHighestRating function returns expected result") {
    var highestRatingresult = HandleMoviesData.outputByHighestRating(listOfMovies);
    assert(highestRatingresult.size>=1);
  }
  test("verify that outputByTotalGross function returns expected result") {
    var totalGrossresult = HandleMoviesData.outputByTotalGross(listOfMovies);
    assert(totalGrossresult(0).equals("1920,$0.0M,Horror"));
    assert(totalGrossresult.size >= 1);
  }

}
