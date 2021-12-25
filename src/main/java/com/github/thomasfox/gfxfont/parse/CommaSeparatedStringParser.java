package com.github.thomasfox.gfxfont.parse;

public class CommaSeparatedStringParser
{
  private final String toParse;

  private int parsePosition;

  private String currentArgument;

  public CommaSeparatedStringParser(String toParse)
  {
    this.toParse = toParse;
  }

  public String nextArgumentString()
  {
    int nextCommaPosition = toParse.indexOf(',', parsePosition);
    if (nextCommaPosition == -1)
    {
      if (parsePosition >= toParse.length())
      {
        currentArgument = null;
        return null;
      }
      nextCommaPosition = toParse.length();
    }
    currentArgument
        = toParse.substring(parsePosition, nextCommaPosition);
    parsePosition = nextCommaPosition + 1;
    return currentArgument;
  }

  public Integer nextArgumentIntNotNull()
  {
    nextArgumentString();
    if (currentArgument == null)
    {
      throw new RuntimeException("Past end in String " + toParse);
    }
    try
    {
      return Integer.parseInt(currentArgument.trim());
    }
    catch(NumberFormatException e)
    {
      throw new RuntimeException("Cannot parse Argument " + currentArgument
          + " within String " + toParse);
    }
  }

  public Byte nextArgumentByteOrNull() {
    nextArgumentString();
    return ByteParser.parseByte(currentArgument);
  }

  int getParsePosition()
  {
    return parsePosition;
  }

  void setParsePosition(int parsePosition)
  {
    this.parsePosition = parsePosition;
  }

  String getCurrentArgument() {
    return currentArgument;
  }
}
