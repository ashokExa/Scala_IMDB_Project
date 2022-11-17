package com.movies.imdbmovies

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ListBuffer

class HandleMoviesDataTest extends AnyFunSuite {
  var superMovies = new SuperMovies("IMDb_All_Genres_etf_clean1.csv", "Movie_Title,Year,Director,Actors,Rating,Runtime(Mins),Censor,Total_Gross,main_genre,side_genre")
  var listOfMovies: ListBuffer[String] = superMovies.readMovies()
  test("verify that outputByHighestRating function returns expected result") {
    val highestRatingresult = HandleMoviesData.outputByHighestRating(listOfMovies)
    assert(highestRatingresult.nonEmpty)
  }
  test("verify that outputByTotalGross function returns expected result") {
    val totalGrossresult = HandleMoviesData.outputByTotalGross(listOfMovies)
    assert(totalGrossresult.head.equals("1920,$0.0M,Horror"))
    assert(totalGrossresult.nonEmpty)
  }

}
