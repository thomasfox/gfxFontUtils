package com.github.thomasfox.gfxfont;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

class GfxGlyphParserTest
{
  private static final String STANDARD_GLYPH_TEST_STRING =
      "{0, 0, 0, 11, 0, 1},      // 0x20 ' '\n    {0, 2, 11, 11, 4, -10}";

  private GfxGlyphParser gfxGlyphParser;

  @BeforeEach
  void beforeEach()
  {
    gfxGlyphParser = new GfxGlyphParser("", "");
  }

  @Test
  void nextGlyphString()
  {
    // arrange
    gfxGlyphParser = new GfxGlyphParser(
        "",
        STANDARD_GLYPH_TEST_STRING);

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
    gfxGlyphParser = new GfxGlyphParser(
        "",
        STANDARD_GLYPH_TEST_STRING);
    gfxGlyphParser.setFontGlyphStringParsePosition(19);

    // act
    gfxGlyphParser.nextGlyphString();

    // assert
    assertThat(gfxGlyphParser.getCurrentGlyphString())
        .isEqualTo("0, 2, 11, 11, 4, -10");
    assertThat(gfxGlyphParser.getFontGlyphStringParsePosition()).isEqualTo(64);
  }

  @Test
  void nextGlyphString_end()
  {
    // arrange
    gfxGlyphParser = new GfxGlyphParser(
        "",
        STANDARD_GLYPH_TEST_STRING);
    gfxGlyphParser.setFontGlyphStringParsePosition(64);

    // act
    gfxGlyphParser.nextGlyphString();

    // assert
    assertThat(gfxGlyphParser.getCurrentGlyphString())
        .isEqualTo(null);
    assertThat(gfxGlyphParser.getFontGlyphStringParsePosition()).isEqualTo(64);
  }

  @Test
  void nextArgumentStringInGlyph()
  {
    // arrange
    gfxGlyphParser.setCurrentGlyphString("0, 2, 11, 11, 4, -10");
    gfxGlyphParser.setWithinGlyphParsePosition(-1);

    // act
    gfxGlyphParser.nextArgumentStringInGlyph();

    // assert
    assertThat(gfxGlyphParser.getWithinGlyphCurrentArgument())
        .isEqualTo("0");
    assertThat(gfxGlyphParser.getWithinGlyphParsePosition()).isEqualTo(2);
  }

  @Test
  void nextArgumentStringInGlyph_followUp()
  {
    // arrange
    gfxGlyphParser.setCurrentGlyphString("0, 2, 11, 11, 4, -10");
    gfxGlyphParser.setWithinGlyphParsePosition(2);

    // act
    gfxGlyphParser.nextArgumentStringInGlyph();

    // assert
    assertThat(gfxGlyphParser.getWithinGlyphCurrentArgument())
        .isEqualTo("2");
    assertThat(gfxGlyphParser.getWithinGlyphParsePosition()).isEqualTo(5);
  }

  @Test
  void nextArgumentStringInGlyph_last()
  {
    // arrange
    gfxGlyphParser.setCurrentGlyphString("0, 2, 11, 11, 4, -10");
    gfxGlyphParser.setWithinGlyphParsePosition(16);

    // act
    gfxGlyphParser.nextArgumentStringInGlyph();

    // assert
    assertThat(gfxGlyphParser.getWithinGlyphCurrentArgument())
        .isEqualTo("-10");
    assertThat(gfxGlyphParser.getWithinGlyphParsePosition()).isEqualTo(21);
  }

  @Test
  void nextArgumentStringInGlyph_pastLast()
  {
    // arrange
    gfxGlyphParser.setCurrentGlyphString("0, 2, 11, 11, 4, -10");
    gfxGlyphParser.setWithinGlyphParsePosition(21);

    // act & assert
    assertThatThrownBy(() -> gfxGlyphParser.nextArgumentStringInGlyph()).hasMessage(
        "past end in current glyph 0, 2, 11, 11, 4, -10");
  }

  @Test
  void nextArgumentIntInGlyph()
  {
    // arrange
    gfxGlyphParser.setCurrentGlyphString("0, 2, 11, 11, 4, -10");
    gfxGlyphParser.setWithinGlyphParsePosition(16);

    // act & assert
    assertThat(gfxGlyphParser.nextArgumentIntInGlyph()).isEqualTo(-10);
  }

  @Test
  void parseGlyph()
  {
    // arrange
    gfxGlyphParser.setCurrentGlyphString("0, 2, 10, 11, 4, -10");
    gfxGlyphParser.setWithinGlyphParsePosition(16); // off value to check it has no effect

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

  @Test
  void parse()
  {
    gfxGlyphParser = new GfxGlyphParser(
        "",
        STANDARD_GLYPH_TEST_STRING);

    // act
    List<Glyph> result = gfxGlyphParser.parse();

    // assert
    assertThat(result.get(0).bitmapOffset).isEqualTo(0);
    assertThat(result.get(0).width).isEqualTo(0);
    assertThat(result.get(0).height).isEqualTo(0);
    assertThat(result.get(0).xAdvance).isEqualTo(11);
    assertThat(result.get(0).xOffset).isEqualTo(0);
    assertThat(result.get(0).yOffset).isEqualTo(1);

    assertThat(result.get(1).bitmapOffset).isEqualTo(0);
    assertThat(result.get(1).width).isEqualTo(2);
    assertThat(result.get(1).height).isEqualTo(11);
    assertThat(result.get(1).xAdvance).isEqualTo(11);
    assertThat(result.get(1).xOffset).isEqualTo(4);
    assertThat(result.get(1).yOffset).isEqualTo(-10);
  }
}