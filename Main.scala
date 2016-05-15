package runtime

import java.io._
import sys.process._
import scala.language.postfixOps

import utils.finder._
import utils.generator._
import utils.mapper._
import utils.unzipper._

object Main extends App {
  def main(args: Array[String]): Unit = {
    var mapper = new Mapper
    var unzipper = new Unzipper
    var finder = new Finder

    def txtFiles = finder.recursiveListFiles(new File("."))
    var zipFiles = finder.recursiveListFiles(new File("."), ".zip")

    unzipper.unzip(zipFiles)

    var mapList = mapper.listOfMapsFromFiles(txtFiles)
    var reducedMap = mapper.reduceMaps(mapList)
    var stringed = reducedMap.map { x => s"['${x._1}', ${x._2}]" }

    var generator = new Generator(stringed.mkString(",\n          "), "chart-test.html")
    generator.sub
  }
}
