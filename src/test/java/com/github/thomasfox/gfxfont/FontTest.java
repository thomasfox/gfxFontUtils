package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FontTest
{
  private Font font;

  @BeforeEach
  void loadFont()
  {
    font = new FontLoader().load("src/test/resources/fontfiles/SomeFont.h");
  }

  @Test
  void getGlyph()
  {
    // arrange
    Glyph expected = new Glyph();
    expected.bitmapOffset = 0;
    expected.xAdvance = 3;
    expected.xOffset = 0;
    expected.yOffset = -4;
    expected.bitmap = new Bitmap(new boolean[0][0]);

    // act & assert
    assertThat(font.getGlyph((byte) 0x20)).isEqualTo(expected);
  }

  @Test
  void getGlyph_below()
  {
    assertThat(font.getGlyph((byte) 0x19)).isNull();
  }

  @Test
  void getGlyph_above()
  {
    assertThat(font.getGlyph((byte) 0x22)).isNull();
  }

  @Test
  void getGlyph_aboveLarge()
  {
    assertThat(font.getGlyph((byte) 0xFF)).isNull();
  }

  @Test
  void getBytes()
  {
    String testString = "dkhfX%!0§ÖäÜ";
    assertThat(font.getBytes(testString)).isEqualTo(testString.getBytes(StandardCharsets.ISO_8859_1));
  }

  @Test
  void getPixelWidth()
  {
    assertThat(font.getPixelWidth(" !  !")).isEqualTo(15);
  }

  @Test
  void asBitmap()
  {
    assertThat(font.asBitmap(" !")).isEqualTo(new Bitmap(new boolean[][] {
        {false, false, false, false},
        {false, false, false, false},
        {false, false, false, false},
        {false, false, false, false},
        {false, false, true, false},
        {false, true, true, false}}));
  }

  @Test
  void getBaseline()
  {
    assertThat(font.getBaseline()).isEqualTo(4);
  }
}