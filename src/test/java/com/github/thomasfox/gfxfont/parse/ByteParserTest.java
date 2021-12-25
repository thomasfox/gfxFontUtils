package com.github.thomasfox.gfxfont.parse;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.github.thomasfox.gfxfont.parse.ByteParser;

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
  void parseByte_null()
  {
    assertThat(ByteParser.parseByte(null)).isNull();
  }
}