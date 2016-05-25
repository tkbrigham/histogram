package histogram

import java.io._

import histogram.utils.finder._
import histogram.utils.generator._
import histogram.utils.mapper._
import histogram.utils.unzipper._

object Main extends App {
  if (args.length == 0) {
    println("ERROR: Please provide target directory")
    println("ex: scala histogram.Main /Users/tkbrigham/folder_of_txt_files")
    System.exit(1)
  } else {
    val finder = new Finder

    // Find and unzip all .zip files (ambivalent of zip contents)
    val zipFiles = finder.recursiveListFiles(new File(args(0)), ".zip")
    var unzipper = new Unzipper
    unzipper.unzip(zipFiles)

    // Find all .txt files, and create collection of words and frequencies
    var mapper = new Mapper
    def txtFiles = finder.recursiveListFiles(new File(args(0)))
    val iterable = mapper.filesToIterable(txtFiles)

    // Generate html file and open it
    var generator = new Generator(iterable, "html/TEMPLATE.html")
    generator.sub
  }
}
