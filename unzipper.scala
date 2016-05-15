package histogram.utils.unzipper

import java.io._
import sys.process._
import scala.language.postfixOps

class Unzipper {
  def unzip(files: Seq[File]) = {
    files.foreach { file =>
      val dir = file.getCanonicalPath.replace(".zip", "")
        // Assumes we don't already have a folder with the name
        // "${zip_file}_unzipped"
        s"unzip -o $file -d ${dir}_unzipped" !
    }
  }
}
