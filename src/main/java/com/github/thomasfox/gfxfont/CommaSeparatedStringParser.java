package com.github.thomasfox.gfxfont;

public class CommaSeparatedStringParser
{
  private String toParse;

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
        throw new RuntimeException("past end in current glyph " + toParse);
      }
      nextCommaPosition = toParse.length();
    }
    currentArgument
        = toParse.substring(parsePosition, nextCommaPosition);
    parsePosition = nextCommaPosition + 1;
    return currentArgument;
  }

  public Integer nextArgumentInt()
  {
    nextArgumentString();
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
