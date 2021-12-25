package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CommaSeparatedStringParserTest
{
  public static final String TO_PARSE = "0,2, 11, 11, 4, -10";
  private CommaSeparatedStringParser commaSeparatedStringParser;

  @Test
  void nextArgumentString()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(TO_PARSE);

    // act
    String result = commaSeparatedStringParser.nextArgumentString();

    // assert
    assertThat(result).isEqualTo("0");
    assertThat(commaSeparatedStringParser.getCurrentArgument()).isEqualTo(result);
    assertThat(commaSeparatedStringParser.getParsePosition()).isEqualTo(2);
  }

  @Test
  void nextArgumentString_followUp()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(TO_PARSE);
    commaSeparatedStringParser.setParsePosition(2);

    // act
    String result = commaSeparatedStringParser.nextArgumentString();

    // assert
    assertThat(result).isEqualTo("2");
    assertThat(commaSeparatedStringParser.getParsePosition()).isEqualTo(4);
  }

  @Test
  void nextArgumentString_followUp2()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(TO_PARSE);
    commaSeparatedStringParser.setParsePosition(12);

    // act
    String result = commaSeparatedStringParser.nextArgumentString();

    // assert
    assertThat(result).isEqualTo(" 4");
    assertThat(commaSeparatedStringParser.getParsePosition()).isEqualTo(15);
  }

  @Test
  void nextArgumentString_last()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(TO_PARSE);
    commaSeparatedStringParser.setParsePosition(15);

    // act
    String result = commaSeparatedStringParser.nextArgumentString();

    // assert
    assertThat(result).isEqualTo(" -10");
    assertThat(commaSeparatedStringParser.getParsePosition()).isEqualTo(20);
  }

  @Test
  void nextArgumentString_pastLast()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(TO_PARSE);
    commaSeparatedStringParser.setParsePosition(20);

    // act & assert
    assertThatThrownBy(() -> commaSeparatedStringParser.nextArgumentString()).hasMessage(
        "past end in current glyph " + TO_PARSE);
  }

  @Test
  void nextArgumentInt()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(TO_PARSE);
    commaSeparatedStringParser.setParsePosition(15);

    // act & assert
    assertThat(commaSeparatedStringParser.nextArgumentInt()).isEqualTo(-10);
  }
}