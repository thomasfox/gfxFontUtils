package com.github.thomasfox.gfxfont.parse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CommaSeparatedStringParserTest
{
  public static final String INT_ARGUMENT_TEST_STRING = "0,2,NaN, 11, 4, -10";

  public static final String BYTE_ARGUMENT_TEST_STRING = "0x01, 0xFF";

  private CommaSeparatedStringParser commaSeparatedStringParser;

  @Test
  void nextArgumentString()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(INT_ARGUMENT_TEST_STRING);

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
    commaSeparatedStringParser = new CommaSeparatedStringParser(INT_ARGUMENT_TEST_STRING);
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
    commaSeparatedStringParser = new CommaSeparatedStringParser(INT_ARGUMENT_TEST_STRING);
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
    commaSeparatedStringParser = new CommaSeparatedStringParser(INT_ARGUMENT_TEST_STRING);
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
    commaSeparatedStringParser = new CommaSeparatedStringParser(INT_ARGUMENT_TEST_STRING);
    commaSeparatedStringParser.setParsePosition(20);

    // act
    String result = commaSeparatedStringParser.nextArgumentString();

    // assert
    assertThat(result).isNull();
    assertThat(commaSeparatedStringParser.getCurrentArgument()).isNull();
    assertThat(commaSeparatedStringParser.getParsePosition()).isEqualTo(20);
  }

  @Test
  void nextArgumentIntNotNull()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(INT_ARGUMENT_TEST_STRING);
    commaSeparatedStringParser.setParsePosition(15);

    // act & assert
    assertThat(commaSeparatedStringParser.nextArgumentIntNotNull()).isEqualTo(-10);
  }

  @Test
  void nextArgumentIntOrNull_pastLast()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(INT_ARGUMENT_TEST_STRING);
    commaSeparatedStringParser.setParsePosition(20);

    // act & assert
    assertThatThrownBy(() -> commaSeparatedStringParser.nextArgumentIntNotNull()).hasMessage(
        "Past end in String " + INT_ARGUMENT_TEST_STRING);

    assertThat(commaSeparatedStringParser.getCurrentArgument()).isNull();
  }

  @Test
  void nextArgumentIntOrNull_noNumber()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(INT_ARGUMENT_TEST_STRING);
    commaSeparatedStringParser.setParsePosition(4);

    // act & assert
    assertThatThrownBy(() -> commaSeparatedStringParser.nextArgumentIntNotNull()).hasMessage(
        "Cannot parse Argument NaN within String " + INT_ARGUMENT_TEST_STRING);

    assertThat(commaSeparatedStringParser.getCurrentArgument()).isEqualTo("NaN");
  }

  @Test
  void nextArgumentByteOrNull()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(BYTE_ARGUMENT_TEST_STRING);

    // act
    Byte result = commaSeparatedStringParser.nextArgumentByteOrNull();

    // assert
    assertThat(result).isEqualTo((byte) 0x01);
    assertThat(commaSeparatedStringParser.getParsePosition()).isEqualTo(5);
  }

  @Test
  void nextArgumentByteOrNull_last()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(BYTE_ARGUMENT_TEST_STRING);
    commaSeparatedStringParser.setParsePosition(5);

    // act
    Byte result = commaSeparatedStringParser.nextArgumentByteOrNull();

    // assert
    assertThat(result).isEqualTo((byte) 0xFF);
    assertThat(commaSeparatedStringParser.getParsePosition()).isEqualTo(11);
  }

  @Test
  void nextArgumentByteOrNull_pastLast()
  {
    // arrange
    commaSeparatedStringParser = new CommaSeparatedStringParser(BYTE_ARGUMENT_TEST_STRING);
    commaSeparatedStringParser.setParsePosition(11);

    // act
    Byte result = commaSeparatedStringParser.nextArgumentByteOrNull();

    // assert
    assertThat(result).isNull();
    assertThat(commaSeparatedStringParser.getCurrentArgument()).isNull();
    assertThat(commaSeparatedStringParser.getParsePosition()).isEqualTo(11);
  }

}