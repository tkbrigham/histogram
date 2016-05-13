import java.io.File

def listFiles(base: File, recursive: Boolean = true): Seq[File] = {
  val files = base.listFiles
  val result = files.filter(_.isFile)
  result ++
    files
      .filter(_.isDirectory)
      .filter(_ => recursive)
      .flatMap(listFiles(_, recursive))
}

println(listFiles(new File("./first_level")))
