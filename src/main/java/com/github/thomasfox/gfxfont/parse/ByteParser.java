package com.github.thomasfox.gfxfont.parse;

public class ByteParser
{
  /**
   * Parses a String in the form 0xXX, where X stand for the two hexadecimal digits of the byte.
   *
   * @param toParse the String to parse, or null.
   *
   * @return the String parsed as byte, or null if <code>toParse</code> was null.
   *
   * @throws NumberFormatException if toParse could not be parsed.
   */
  public static Byte parseByte(String toParse)
  {
    if (toParse == null)
    {
      return null;
    }
    toParse = toParse.trim();
    if (!toParse.toLowerCase().startsWith("0x"))
    {
      throw new NumberFormatException("The non-whitespace part of toParse " + toParse
          + " does not start with 0x");
    }
    Integer intResult = Integer.decode(toParse.trim());
    return (byte) (intResult & 0xFF);
  }

  /**
   * Prints a byte in the form 0xXX, where X stand for the two hexadecimal digits of the byte.
   *
   * @param toConvert the byte to convert to hex form
   *
   * @return the hex form of the byte, never null.
   */
  public static String toHexString(byte toConvert)
  {
    return String.format("0x%02X", toConvert);
  }
}
