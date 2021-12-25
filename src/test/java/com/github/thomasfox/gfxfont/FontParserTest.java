package com.github.thomasfox.gfxfont;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FontParserTest
{
  private FontParser fontParser = new FontParser("");

  @Test
  void getContentInCurlyBracketsAfterKeyword()
  {
    assertThat(fontParser.getContentInCurlyBracketsAfterKeyword(
            "myKeyword",
            "myKeyword 123 f {expec\nted} 456"))
        .isEqualTo("expec\nted");
  }

  @Test
  void getContentInCurlyBracketsAfterKeyword_nested()
  {
    assertThat(fontParser.getContentInCurlyBracketsAfterKeyword(
        "myKeyword",
        "myKeyword {{SomeOther},{}expected}"))
        .isEqualTo("{SomeOther},{}expected");
  }

  @Test
  void getContentInCurlyBracketsAfterKeyword_nestedWithTrailingBraces()
  {
    assertThat(fontParser.getContentInCurlyBracketsAfterKeyword(
        "myKeyword",
        "myKeyword { expected }{}{}"))
        .isEqualTo(" expected ");
  }

  @Test
  void getTrimmedContentAfterRoundClosingBracket()
  {
    assertThat(fontParser.getTrimmedContentAfterRoundClosingBracket(
        "(uint8_t *) FreeMono9pt7bBitmaps "))
        .isEqualTo("FreeMono9pt7bBitmaps");
  }

  @Test
  void parse() throws IOException
  {
    try (FileInputStream fis = new FileInputStream("src/test/resources/fontfiles/SomeFont.h"))
    {
      // arrange
      String fileContent = new String(fis.readAllBytes(), StandardCharsets.ISO_8859_1);

      fontParser = new FontParser(fileContent);

      Glyph expectedFirstGlyph = new Glyph();
      expectedFirstGlyph.bitmapOffset = 0;
      expectedFirstGlyph.width = 0;
      expectedFirstGlyph.height = 0;
      expectedFirstGlyph.xAdvance = 3;
      expectedFirstGlyph.xOffset = 0;
      expectedFirstGlyph.yOffset = 1;
      expectedFirstGlyph.bitmap = new boolean[0][0];

      Glyph expectedSecondGlyph = new Glyph();
      expectedSecondGlyph.bitmapOffset = 0;
      expectedSecondGlyph.width = 2;
      expectedSecondGlyph.height = 3;
      expectedSecondGlyph.xAdvance = 3;
      expectedSecondGlyph.xOffset = 4;
      expectedSecondGlyph.yOffset = -10;
      expectedSecondGlyph.bitmap = new boolean[][]{
          {false, true, false},
          {true, true, false}
      };

      // act
      Font result = fontParser.parse();

      // assert
      assertThat(result.getGlyphList()).containsExactly(
          expectedFirstGlyph,
          expectedSecondGlyph);
      assertThat(result.getFirstChar()).isEqualTo((byte) 0x20);
      assertThat(result.getLastChar()).isEqualTo((byte) 0x21);
      assertThat(result.getLineHeight()).isEqualTo(3);
    }
  }
}