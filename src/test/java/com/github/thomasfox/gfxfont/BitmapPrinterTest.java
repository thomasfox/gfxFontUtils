package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class BitmapPrinterTest
{
  @Test
  void printBitmap()
  {
    // arrange
    boolean[][] toPrint = {{true, false, false}, {false, true, false}};
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);

    // act
    BitmapPrinter.printBitmap(toPrint, printStream);

    // assert
    String result = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1);
    assertThat(result).isEqualTo(
        "X.\n" +
        ".X\n" +
        "..\n" +
        "\n");
  }
}