package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GfxFontBitmapParserTest
{
  public static final String STANDARD_BITMAP_TEST_STRING = "0x01, 0xFF";

  private GfxFontBitmapParser gfxFontBitmapParser;

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