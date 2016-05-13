import java.io.File

object Main {
  def main(args: Array[String]) = {
    var mapper = new Mapper
    var map1 = mapper.generate("test.txt")
    var map2 = mapper.generate("test2.txt")

    var finder = new FileFinder
    var files = finder.recursiveListFiles(new File("."))

    println(files)
  }

  class Mapper {
    def generate(file: String): Map[String, Int] = {
      var map = scala.io.Source.fromFile(file)
        .getLines
        .flatMap(_.split("\\W+"))
        .foldLeft(Map.empty[String, Int]) {
          (count, word) => count + (word -> (count.getOrElse(word, 0) + 1))
        }
      return map
    }

    def addMaps(m1: Map[String, Int], m2: Map[String, Int]) = {
      m1 ++ m2.map{ case (k,v) => k -> (v + m1.getOrElse(k,0)) }
    }
  }

  class FileFinder {
    def recursiveListFiles(base: File, recursive: Boolean = true, hidden:
Boolean = false): Seq[File] = {
      val files = base.listFiles
        val result = files.filter(_.isFile)
        result ++
        files
        .filter(_.isDirectory)
        .filter(_.getName.head.toString == ".")
        .filter(_ => recursive)
        .flatMap(recursiveListFiles(_, recursive))
    }
  }
}
