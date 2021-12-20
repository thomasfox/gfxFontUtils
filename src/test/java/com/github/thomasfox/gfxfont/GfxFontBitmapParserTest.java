package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GfxFontBitmapParserTest
{
  public static final String STANDARD_BITMAP_TEST_STRING = "0x01, 0xFF";

  private GfxFontBitmapParser gfxFontBitmapParser;

  @Test
  void nextArgumentInFontBitmapsString()
  {
    // arrange
    gfxFontBitmapParser = new GfxFontBitmapParser(STANDARD_BITMAP_TEST_STRING);

    // act
    Byte result = gfxFontBitmapParser.nextArgumentInFontBitmapsString();

    // assert
    assertThat(result).isEqualTo((byte) 0x01);
    assertThat(gfxFontBitmapParser.getFontBitmapsStringParsePosition()).isEqualTo(5);
  }

  @Test
  void nextArgumentInFontBitmapsString_last()
  {
    // arrange
    gfxFontBitmapParser = new GfxFontBitmapParser(STANDARD_BITMAP_TEST_STRING);
    gfxFontBitmapParser.setFontBitmapsStringParsePosition(5);

    // act
    Byte result = gfxFontBitmapParser.nextArgumentInFontBitmapsString();

    // assert
    assertThat(result).isEqualTo((byte) 0xFF);
    assertThat(gfxFontBitmapParser.getFontBitmapsStringParsePosition()).isEqualTo(11);
  }

  @Test
  void nextArgumentInFontBitmapsString_pastLast()
  {
    // arrange
    gfxFontBitmapParser = new GfxFontBitmapParser(STANDARD_BITMAP_TEST_STRING);
    gfxFontBitmapParser.setFontBitmapsStringParsePosition(11);

    // act
    Byte result = gfxFontBitmapParser.nextArgumentInFontBitmapsString();

    // assert
    assertThat(result).isEqualTo(null);
    assertThat(gfxFontBitmapParser.getFontBitmapsStringParsePosition()).isEqualTo(11);
  }

  @Test
  void parse()
  {
    // arrange
    gfxFontBitmapParser = new GfxFontBitmapParser(STANDARD_BITMAP_TEST_STRING);

    // act
    byte[] result = gfxFontBitmapParser.parse();

    // assert
    assertThat(result).isEqualTo(new byte[] {(byte) 0x01, (byte) 0xFF});
  }
}