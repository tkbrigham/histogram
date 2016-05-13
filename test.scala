object Main {
  def main(args: Array[String]) = {
    val map = scala.io.Source.fromFile(args(0))
      .getLines
      .flatMap(_.split("\\W+"))
      .foldLeft(Map.empty[String, Int]) {
        (count, word) => count + (word -> (count.getOrElse(word, 0) + 1))
      }
    println(map)
  }
}
