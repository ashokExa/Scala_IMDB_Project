package com.movies.imdbmovies

import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite

import java.io.FileNotFoundException
import java.nio.file.Paths
import scala.reflect.io.Directory

class SuperMoviesTest extends AnyFunSuite with BeforeAndAfter {
  var superMovies = new SuperMovies("IMDb_All_Genres_etf_clean1.csv", "Movie_Title,Year,Director,Actors,Rating,Runtime(Mins),Censor,Total_Gross,main_genre,side_genre")
  var result = superMovies.readMovies()

  after {
    val outputDirectory = new Directory( Paths.get(superMovies.outputDirectory).toFile )
    outputDirectory.deleteRecursively()
  }

  test("verify that file read success and  got the result properly") {
    assert(result.nonEmpty)
    assert(result.head.equals("Kantara,2022,Rishab Shetty,\"Rishab Shetty, Sapthami Gowda, Kishore Kumar G., Achyuth Kumar\",9.3,148,UA,Gross Unkown,Action,\" Adventure,  Drama\""))
  }

  test("verify that output movies data written in the output path properly") {
    val writtenStatus = superMovies.writeMovieDataBasedOnLogic(s"Movie_Data_By_Rating.csv", result, HandleMoviesData.outputByHighestRating)
    assert(writtenStatus.equals("Data saved successfully"))
  }

}
