import java.io.File

object Main {
  def main(args: Array[String]): Unit = {
    var mapper = new Mapper
    var generator = new HtmlGen

    var finder = new FileFinder
    var files = finder.recursiveListFiles(new File("."))

    generator.sub("sample.html")

    //var mapList = mapper.listOfMapsFromFiles(files)
    //var reducedMap = mapper.reduceMaps(mapList)
    //println(reducedMap)
  }

  class HtmlGen {
    def sub(file: String) = {
      var lines = io.Source.fromFile(file).getLines
      var testing = lines.find(_.contains("{{ array }}"))
      println(testing)
      println(testing.getClass)
      //println(lines.next())
      //var testing = lines.map(in => if (in 
    }
  }

  class Mapper {
    def reduceMaps(maps: Seq[Map[String, Int]]) = {
      maps
        .foldLeft(maps(0))((b,a) => addMaps(b,a))
        .filterKeys(_ != "")
    }

    def listOfMapsFromFiles(files: Seq[File]): Seq[Map[String, Int]] = {
      var head = files.head.toString
      var tail = files.tail
      var results = Seq(generate(head))

      if (tail.isEmpty) return results else results ++ listOfMapsFromFiles(tail)
    }

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
      val files = base.listFiles.filter(!_.isHidden)
        val result = files.filter(_.isFile)
        result ++
          files
          .filter(_.isDirectory)
          .filter(_ => recursive)
          .flatMap(recursiveListFiles(_, recursive))
    }
  }
}
