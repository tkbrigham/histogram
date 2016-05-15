package histogram.utils.generator

import java.io._
import sys.process._
import scala.language.postfixOps

class Generator(str: String, file: String) {
  def sub = {
    var ans = for (line <- io.Source.fromFile(file).getLines) yield
      (line.replace("{{ array }}", str))
      val pw = new PrintWriter(new File("TEST.html"))
      for (line <- ans) pw.write(line + "\n")
        pw.close
          "open TEST.html" !
  }
}
