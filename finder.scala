package finder

import java.io._

class Finder {
  def recursiveListFiles(base: File, filetype: String = ".txt"): Seq[File] = {
    // Assumes we don't want hidden files
    val files = base.listFiles.filter(!_.isHidden)
      val result = files.filter(_.isFile).filter(_.getName.endsWith(filetype))
      result ++
      files
      .filter(_.isDirectory)
      .flatMap(recursiveListFiles(_, filetype))
  }
}
