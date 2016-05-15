package runtime

import java.io._
import sys.process._
import scala.language.postfixOps
import finder._

object Main extends App {
  var finder = new Finder

  def txtFiles = finder.recursiveListFiles(new File("."))
  println(txtFiles)
}
