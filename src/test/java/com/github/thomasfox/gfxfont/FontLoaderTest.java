package com.github.thomasfox.gfxfont;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FontLoaderTest
{
  private FontLoader fontLoader = new FontLoader();

  @Test
  void getContentInCurlyBracketsAfterKeyword()
  {
    assertThat(fontLoader.getContentInCurlyBracketsAfterKeyword(
            "myKeyword",
            "myKeyword 123 f {expec\nted} 456"))
        .isEqualTo("expec\nted");
  }

  @Test
  void getContentInCurlyBracketsAfterKeyword_nested()
  {
    assertThat(fontLoader.getContentInCurlyBracketsAfterKeyword(
        "myKeyword",
        "myKeyword {{SomeOther},{}expected}"))
        .isEqualTo("{SomeOther},{}expected");
  }

  @Test
  void getContentInCurlyBracketsAfterKeyword_nestedWithTrailingBraces()
  {
    assertThat(fontLoader.getContentInCurlyBracketsAfterKeyword(
        "myKeyword",
        "myKeyword { expected }{}{}"))
        .isEqualTo(" expected ");
  }

  @Test
  void getTrimmedContentBetweenRoundClosingBracketAndComma()
  {
    assertThat(fontLoader.getTrimmedContentBetweenRoundClosingBracketAndComma(
        "(uint8_t *) FreeMono9pt7bBitmaps ,  124"))
        .isEqualTo("FreeMono9pt7bBitmaps");
  }

  @Test
  void getAfterNthComma() {
    assertThat(fontLoader.getAfterNthComma(
        3, ", ,, expected ,   ,,,"))
        .isEqualTo(" expected ,   ,,,");
  }

  @Test
  void getAfterNthComma_exception() {
    assertThatThrownBy(() -> fontLoader.getAfterNthComma(
        10, ", ,, expected ,   ,,,"))
        .hasMessage("No 10 commas in , ,, expected ,   ,,,");
  }

  @Test
  void load()
  {
    fontLoader.load("src/test/resources/fontfiles/SomeFont.h");
    assertThat(fontLoader.getFontString()).startsWith("(uint8_t *)").endsWith("0x21, 18");
    assertThat(fontLoader.getBitmapsString().trim()).startsWith("0x00, 0x11, 0x22, ").endsWith("0xDD, 0xEE, 0xFF");
    assertThat(fontLoader.getGlyphsString().trim()).startsWith("{0, 0, 0, 11, 0, 1},").endsWith("11, 4, -10}");
  }
}