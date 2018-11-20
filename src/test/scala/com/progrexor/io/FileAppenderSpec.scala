package com.progrexor.io

class FileAppenderSpec extends org.scalatest.FlatSpec {

  it should "print information" in {
    val fa = FileAppender("")
    fa.info
  }

  it should "create correct target file" in {
    val header = "my header\n"

    val bytesWritten = FileAppender("output.txt")
      .add(header, "src/test/resources/file1.txt")

    assert(bytesWritten === 37)
  }
}
