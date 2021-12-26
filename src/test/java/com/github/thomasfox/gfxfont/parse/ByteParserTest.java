package com.github.thomasfox.gfxfont.parse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ByteParserTest
{
  @Test
  void parseByte_smallValue()
  {
    assertThat(ByteParser.parseByte("0x01")).isEqualTo((byte) 0x01);
  }

  @Test
  void parseByte_largeValue()
  {
    assertThat(ByteParser.parseByte("0xFF")).isEqualTo((byte) 0xFF);
  }

  @Test
  void parseByte_whitespaceValue()
  {
    assertThat(ByteParser.parseByte("  \n  0xAC  \n\n ")).isEqualTo((byte) 0xAC);
  }

  @Test
  void parseByte_null()
  {
    assertThat(ByteParser.parseByte(null)).isNull();
  }

  @Test
  void parseByte_wrongStart()
  {
    assertThatThrownBy(() -> ByteParser.parseByte("  \n  0yFF"))
        .hasMessage("The non-whitespace part of toParse 0yFF does not start with 0x");
  }

  @Test
  void parseByte_noNumber()
  {
    assertThatThrownBy(() -> ByteParser.parseByte("0xGH"))
        .isInstanceOf(NumberFormatException.class);
  }

  @Test
  void toHexString_largeValue()
  {
    assertThat(ByteParser.toHexString((byte) 0xFE)).isEqualTo("0xFE");
  }

  @Test
  void toHexString_smallValue()
  {
    assertThat(ByteParser.toHexString((byte) 0x25)).isEqualTo("0x25");
  }
}