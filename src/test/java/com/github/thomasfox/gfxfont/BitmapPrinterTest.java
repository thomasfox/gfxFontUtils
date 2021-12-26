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
    new BitmapPrinter(toPrint).printBitmap(printStream);

    // assert
    String result = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1);
    assertThat(result).isEqualTo(
        "X.\n" +
        ".X\n" +
        "..\n" +
        "\n");
  }

  @Test
  void printBitmapAsHex_danglingBitAtEnd()
  {
    // arrange
    boolean[][] toPrint = {
        {true, false, false, false, false, false, false},
        {true, true, false, false, false, false, false},
        {true, false, false, false, false, false, false},
        {true, false, false, false, false, false, false},
        {false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false},
        {true, false, false, false, false, false, true}};
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);

    // act
    new BitmapPrinter(toPrint).printBitmapAsHex(printStream);

    // assert
    String result = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1);
    assertThat(result).isEqualTo(
        "0xF2, 0x80, 0x00, 0x00, 0x00, 0x00, 0x80\n");
  }

  @Test
  void printBitmapAsHex_withLineEnd()
  {
    // arrange
    boolean[][] toPrint = {
        {true, true, false, false, false, false, false, false, false, false, true},
        {true, false, false, false, false, false, false, false, false, false, false},
        {true, false, false, false, false, false, false, false, false, false, false},
        {true, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false},
        {true, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, true}};
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);

    // act
    new BitmapPrinter(toPrint).printBitmapAsHex(printStream);

    // assert
    String result = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1);
    assertThat(result).isEqualTo(
        "0xF2, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, \n" +
        "0x81\n");
  }

}