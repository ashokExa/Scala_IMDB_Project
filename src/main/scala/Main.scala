import com.movies.imdbmovies.{HandleMoviesData, SuperMovies}
import scala.collection.mutable.ListBuffer
object Main {
  def main(args: Array[String]): Unit = {
    val timeInMillis = System.currentTimeMillis()
    val movieDatabasedOnRating = new SuperMovies("/Users/u1126039/Downloads/IMDb_All_Genres_etf_clean1.csv","Movie_Title,Year,Director,Actors,Rating,Runtime(Mins),Censor,Total_Gross,main_genre,side_genre")
    val movieDatabasedOnTotalGross = new SuperMovies("/Users/u1126039/Downloads/IMDb_All_Genres_etf_clean1.csv","Year,Total_Gross,main_genre")
    val moviesList:ListBuffer[String] =movieDatabasedOnRating.readMovies()
    val resultByRating = movieDatabasedOnRating.writeMovieDataBasedOnLogic(s"/Users/u1126039/Downloads/Movie_Data_By_Rating$timeInMillis.csv",moviesList,HandleMoviesData.outputByHighestRating)
    val resultByTotalGross = movieDatabasedOnTotalGross.writeMovieDataBasedOnLogic(s"/Users/u1126039/Downloads/Movie_Data_By_Total_Gross$timeInMillis.csv",moviesList,HandleMoviesData.outputByTotalGross)
    println(resultByRating+" By Rating")
    println(resultByTotalGross+" By Total Gross")

  }
}
