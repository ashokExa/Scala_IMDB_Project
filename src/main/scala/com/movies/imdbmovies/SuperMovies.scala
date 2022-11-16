package com.movies.imdbmovies

import java.io.{File, FileNotFoundException, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.collection.mutable.ListBuffer
import scala.io.Source

trait Movies {
  def readMovies(): ListBuffer[String]
}

class SuperMovies(var resourceName: String, var fileHeader: String) extends Movies {
  val outputDirectory = "output/"

  def readMovies(): ListBuffer[String] = {
    val sourceFile = Source.fromResource(resourceName)
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

  def writeMovieDataBasedOnLogic(outputFilename: String, moviesList: ListBuffer[String], handleMoviesData: ListBuffer[String] => ListBuffer[String]): String = {
    val writableMovieData = handleMoviesData(moviesList)

    createOutputDirectory()
    val outputPath = outputDirectory + outputFilename
    val printWriter = new PrintWriter(new File(outputPath))
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

  def createOutputDirectory(): Unit = {
    if (!Files.exists(Paths.get(outputDirectory)))
      Files.createDirectory(Paths.get(outputDirectory))
  }
}

