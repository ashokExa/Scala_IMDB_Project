package com.movies.imdbmovies
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
object HandleMoviesData {

  def outputByHighestRating(filesData:ListBuffer[String]):ListBuffer[String]={
    var moviesDataByYear:mutable.LinkedHashMap[String,String] = new mutable.LinkedHashMap[String,String]()
    var moviesDataByYearList:ListBuffer[String] = ListBuffer[String]()
    for(movie<-filesData){
      var moviesInfo = movie.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
      if(moviesDataByYear.contains(moviesInfo(1))){
        var movieDetails = moviesDataByYear(moviesInfo(1)).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        if(moviesInfo(4).toDouble > movieDetails(4).toDouble){
          moviesDataByYear(moviesInfo(1)) = movie;
        }
      } else {
        moviesDataByYear += (moviesInfo(1)-> movie)
      }
    }
    var sortedMovieList = mutable.LinkedHashMap(moviesDataByYear.toSeq.sortWith(_._1 > _._1):_*);
    sortedMovieList.keys.foreach(key=>{
      moviesDataByYearList+=moviesDataByYear(key);
    })
    moviesDataByYearList
  }

  def outputByTotalGross(moviesData:ListBuffer[String]):ListBuffer[String]={
    var movieInformations:mutable.ListBuffer[MovieInfo] = ListBuffer[MovieInfo]();
    var movieInfoByGenre:mutable.ListBuffer[MovieInfo] =ListBuffer[MovieInfo]();
    var movieInfoByYear:mutable.ListBuffer[MovieInfo] =ListBuffer[MovieInfo]();
    var movieDataAfterTotalGrossLogic:mutable.LinkedHashMap[String,String] = mutable.LinkedHashMap[String,String]();
    var sortedFinalizedMovieData:ListBuffer[String] = ListBuffer[String]();
    moviesData.foreach(movie=>{
      var fullmovieDetails = movie.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
      movieInformations+= new MovieInfo(fullmovieDetails(1).toInt,fullmovieDetails(7),fullmovieDetails(8));
    })
    movieInformations.groupBy(_.mainGenre).keys.foreach(key=>{
      movieInformations.groupBy(_.mainGenre)(key).foreach(currentMovieInfo=>{
        movieInfoByGenre += new MovieInfo(currentMovieInfo.year,currentMovieInfo.totalGross,currentMovieInfo.mainGenre);
      })
    })

    movieInfoByGenre.groupBy(_.year).keys.foreach(key => {
      movieInfoByGenre.groupBy(_.year)(key).foreach(currentMovieData => {
        movieInfoByYear += new MovieInfo(currentMovieData.year, currentMovieData.totalGross, currentMovieData.mainGenre);
      })
    })
    movieInfoByYear.foreach(movieByYear=> {
      var movieMapWithKey = movieByYear.mainGenre+movieByYear.year.toString;
      var movieTotalGross : Double = if (movieByYear.totalGross.equals("Gross Unkown")) 0.0 else movieByYear.totalGross.substring(1, movieByYear.totalGross.length - 1).toDouble;
      if (movieDataAfterTotalGrossLogic.contains(movieMapWithKey)) {
        var totalGross = movieDataAfterTotalGrossLogic(movieMapWithKey).split(",")(1);
        var afterTotalGrossCalculation = totalGross.substring(1,totalGross.length-1).toDouble + movieTotalGross;;
        movieDataAfterTotalGrossLogic(movieByYear.mainGenre+movieByYear.year.toString) = movieByYear.year+","+"$"+afterTotalGrossCalculation+"M"+","+movieByYear.mainGenre
      }
      else {
        var currentmovieInfo = movieByYear.year+","+"$"+movieTotalGross+"M"+","+movieByYear.mainGenre;
        movieDataAfterTotalGrossLogic += (movieMapWithKey -> currentmovieInfo);
      }
    }
    )
    sortedFinalizedMovieData = sortMoviesData(movieDataAfterTotalGrossLogic)
    sortedFinalizedMovieData;
  }

  def sortMoviesData(moviesData:mutable.LinkedHashMap[String,String]):ListBuffer[String]={
    var unSortedfinalizedMovieData :ListBuffer[MovieInfo] = ListBuffer[MovieInfo]();
    var sortedFinalizedMovieData :ListBuffer[String] = ListBuffer[String]();
    moviesData.toSeq.sorted.foreach(data => {
      var dataInfo = data.toString().split(",");
      unSortedfinalizedMovieData += new MovieInfo(dataInfo(1).toInt, dataInfo(2), dataInfo(3).substring(0, dataInfo(3).length - 1));
    })
    unSortedfinalizedMovieData.sortBy(_.year).foreach(movie => {
      sortedFinalizedMovieData += movie.year + "," + movie.totalGross + "," + movie.mainGenre
    })
    sortedFinalizedMovieData
  }

}

