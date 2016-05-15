package histogram.utils.generator

import java.io._
import sys.process._
import scala.language.postfixOps

class Generator(itr: Iterable[String], file: String) {
  def sub = {
    val ans = for (line <- io.Source.fromFile(file).getLines) yield
      (line.replace("{{ array }}", itr.mkString(",")))
    val htmlName: String = "graph.html"
    val pw = new PrintWriter(new File(htmlName))
    for (line <- ans) pw.write(line + "\n")
    pw.close
    s"open $htmlName" !
  }
}
