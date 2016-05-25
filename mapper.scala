package histogram.utils.mapper

import java.io._

class Mapper {
  def reduceMaps(maps: Seq[Map[String, Int]]) = {
    maps
      .foldLeft(Map("" -> 0))((b,a) => addMaps(b,a))
      .filterKeys(_ != "")
  }

  def listOfMapsFromFiles(files: Seq[File]): Seq[Map[String, Int]] = {
    val head = files.head.toString
      val tail = files.tail
      var results = Seq(generate(head))

      if (tail.isEmpty) return results else results ++ listOfMapsFromFiles(tail)
  }

  def generate(file: String): Map[String, Int] = {
    scala.io.Source.fromFile(file)
      .getLines
      .flatMap(_.split("\\W+"))
      .foldLeft(Map.empty[String, Int]) {
        (count, word) => count + (word -> (count.getOrElse(word, 0) + 1))
      }
  }

  def addMaps(m1: Map[String, Int], m2: Map[String, Int]) = {
    m1 ++ m2.map{ case (k,v) => k -> (v + m1.getOrElse(k,0)) }
  }

  def filesToIterable(files: Seq[File]): Iterable[String] = {
    val mapList = listOfMapsFromFiles(files)
    val reducedMap = reduceMaps(mapList)
    reducedMap.map { x => s"['${x._1}', ${x._2}]" }
  }
}
