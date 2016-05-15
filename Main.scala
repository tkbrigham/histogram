package histogram

import java.io._
import sys.process._
import scala.language.postfixOps

import histogram.utils.finder._
import histogram.utils.generator._
import histogram.utils.mapper._
import histogram.utils.unzipper._

object Main extends App {
  if (args.length == 0) {
    println("Please provide targetted directory")
    System.exit(1)
  } else {
    val finder = new Finder

    def txtFiles = finder.recursiveListFiles(new File(args(0)))
    val zipFiles = finder.recursiveListFiles(new File(args(0)), ".zip")

    var unzipper = new Unzipper
    unzipper.unzip(zipFiles)

    var mapper = new Mapper
    val iterable = mapper.filesToIterable(txtFiles)
    var generator = new Generator(iterable, "html/TEMPLATE.html")
    generator.sub
  }
}
