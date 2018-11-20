package com.progrexor.io

import java.io.{ByteArrayInputStream, InputStream, SequenceInputStream}
import java.nio.file._

class FileAppender(path: Path) {

  def add(part1String: String, part2File: String): Long = {
    concatenate(
      new ByteArrayInputStream(part1String.getBytes),
      Files.newInputStream(Paths.get(part2File))
    )
  }

  private def concatenate(inputStream1: InputStream, inputStream2: InputStream): Long =
    Files.copy(
      new SequenceInputStream(inputStream1, inputStream2),
      path,
      StandardCopyOption.REPLACE_EXISTING
    )

  def info = {
    println(s"FileSystem: ${path.getFileSystem}")
    println(s"AbsolutePath: ${path.toAbsolutePath}")
    println(s"Path: ${path}")
  }
}

object FileAppender {
  def apply(path: String): FileAppender = new FileAppender(Paths.get(path))
  def apply(path: Path): FileAppender = new FileAppender(path)

  def main(args: Array[String]): Unit = {
    FileAppender(args(0)).add(args(1), args(2))
  }
}
