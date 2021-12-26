package com.github.thomasfox.gfxfont.parse;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.github.thomasfox.gfxfont.Glyph;

class GfxFontGlyphsStringParserTest
{
  private static final String STANDARD_GLYPH_TEST_STRING
      = "{0, 0, 0, 11, 0, 1},      \n    {0, 2, 11, 11, 4, -10}";

  private GfxFontGlyphsStringParser gfxGlyphParser;

  @Test
  void removeCommentsInGlyphsString_noCommentAtEnd()
  {
    // arrange
    gfxGlyphParser = new GfxFontGlyphsStringParser(
        "{0, 0, 0, 11, 0, 1},      // 0x20 '{'\n    {0, 2, 11, 11, 4, -10}");

    // act
    gfxGlyphParser.removeCommentsInGlyphsString();

    // assert
    assertThat(gfxGlyphParser.getGfxFontGlyphsString())
        .isEqualTo("{0, 0, 0, 11, 0, 1},      \n    {0, 2, 11, 11, 4, -10}");
  }

  @Test
  void removeCommentsInGlyphsString_commentAtEnd()
  {
    // arrange
    gfxGlyphParser = new GfxFontGlyphsStringParser(
        "{0, 0, 0, 11, 0, 1},      // 0x20 '{'\n    {0, 2, 11, 11, 4, -10} //commentAtEnd");

    // act
    gfxGlyphParser.removeCommentsInGlyphsString();

    // assert
    assertThat(gfxGlyphParser.getGfxFontGlyphsString())
        .isEqualTo("{0, 0, 0, 11, 0, 1},      \n    {0, 2, 11, 11, 4, -10} ");
  }

  @Test
  void nextGlyphString()
  {
    // arrange
    gfxGlyphParser = new GfxFontGlyphsStringParser(STANDARD_GLYPH_TEST_STRING);

    // act
    gfxGlyphParser.nextGlyphString();

    // assert
    assertThat(gfxGlyphParser.getCurrentGlyphString())
        .isEqualTo("0, 0, 0, 11, 0, 1");
    assertThat(gfxGlyphParser.getFontGlyphStringParsePosition()).isEqualTo(19);
  }

  @Test
  void nextGlyphString_followUp()
  {
    // arrange
    gfxGlyphParser = new GfxFontGlyphsStringParser(STANDARD_GLYPH_TEST_STRING);
    gfxGlyphParser.setFontGlyphStringParsePosition(19);

    // act
    gfxGlyphParser.nextGlyphString();

    // assert
    assertThat(gfxGlyphParser.getCurrentGlyphString())
        .isEqualTo("0, 2, 11, 11, 4, -10");
    assertThat(gfxGlyphParser.getFontGlyphStringParsePosition()).isEqualTo(53);
  }

  @Test
  void nextGlyphString_end()
  {
    // arrange
    gfxGlyphParser = new GfxFontGlyphsStringParser(STANDARD_GLYPH_TEST_STRING);
    gfxGlyphParser.setFontGlyphStringParsePosition(53);

    // act
    gfxGlyphParser.nextGlyphString();

    // assert
    assertThat(gfxGlyphParser.getCurrentGlyphString())
        .isEqualTo(null);
    assertThat(gfxGlyphParser.getFontGlyphStringParsePosition()).isEqualTo(53);
  }

  @Test
  void parseGlyphString()
  {
    // arrange
    gfxGlyphParser = new GfxFontGlyphsStringParser("");
    gfxGlyphParser.setCurrentGlyphString("0, 2, 10, 11, 4, -10");

    // act
    Glyph result = gfxGlyphParser.parseGlyphString();

    // assert
    assertThat(result.bitmapOffset).isEqualTo(0);
    assertThat(result.width).isEqualTo(2);
    assertThat(result.height).isEqualTo(10);
    assertThat(result.xAdvance).isEqualTo(11);
    assertThat(result.xOffset).isEqualTo(4);
    assertThat(result.yOffset).isEqualTo(-10);
  }
}