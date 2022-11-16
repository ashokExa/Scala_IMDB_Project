package com.movies.imdbmovies
import java.io.{File, FileNotFoundException, PrintWriter}
import scala.io.Source
import scala.collection.mutable.ListBuffer

trait Movies {
  def readMovies():ListBuffer[String]
}

class SuperMovies(var filePath: String, var fileHeader:String) extends Movies {
  def readMovies(): ListBuffer[String] = {
    val sourceFile = Source.fromFile(filePath)
    val inputFileData = ListBuffer[String]()
    try {
      for (fileData <- sourceFile.getLines().drop(1)) {
        inputFileData += fileData.mkString
      }
      inputFileData
    }
    catch {
      case e: FileNotFoundException => e.printStackTrace(); ListBuffer()
      case e: Exception => e.printStackTrace(); ListBuffer()
    }
    finally {
      sourceFile.close()
    }
  }

  def writeMovieDataBasedOnLogic(outputLocation:String, moviesList:ListBuffer[String], handleMoviesData: ListBuffer[String] =>ListBuffer[String]): String ={
    val writableMovieData = handleMoviesData(moviesList)
    val printWriter = new PrintWriter(new File(outputLocation))
    try {
      printWriter.write(fileHeader + "\n")
      for (finalList <- writableMovieData) {
        printWriter.write(finalList + "\n")
      }
      "Data saved successfully"
    }
    catch {
      case e: FileNotFoundException => e.printStackTrace(); "Failed to save Data"
      case e: Exception => e.printStackTrace(); "Failed to save Data"
    }
    finally {
      printWriter.close()
    }

  }
}

