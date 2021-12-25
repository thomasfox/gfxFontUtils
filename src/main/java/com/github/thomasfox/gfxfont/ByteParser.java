package com.github.thomasfox.gfxfont;

public class ByteParser
{
  public static Byte parseByte(String toParse)
  {
    if (toParse == null)
    {
      return null;
    }
    Integer intResult = Integer.decode(toParse.trim());
    return (byte) (intResult & 0xFF);
  }
}
