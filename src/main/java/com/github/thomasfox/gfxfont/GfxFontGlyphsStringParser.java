package com.github.thomasfox.gfxfont;

import java.util.ArrayList;
import java.util.List;

class GfxFontGlyphsStringParser
{
  private String gfxFontGlyphsString;

  private int fontGlyphStringParsePosition = 0;
  private String currentGlyphString = null;
  private int withinGlyphParsePosition;
  private String withinGlyphCurrentArgument;

  public GfxFontGlyphsStringParser(String gfxFontGlyphsString)
  {
    this.gfxFontGlyphsString = gfxFontGlyphsString;
  }

  public List<Glyph> parse()
  {
    removeCommentsInGlyphsString();
    List<Glyph> result = new ArrayList<>();
    while (true)
    {
      nextGlyphString();
      if (currentGlyphString == null)
      {
        break;
      }
      Glyph glyph = parseGlyphString();
      result.add(glyph);
    }
    return result;
  }

  void removeCommentsInGlyphsString()
  {
    int parsePosition = 0;
    StringBuilder result = new StringBuilder();
    while (true)
    {
      int commentPosition = gfxFontGlyphsString.indexOf("//", parsePosition);
      if (commentPosition == -1)
      {
        // no more comments
        result.append(gfxFontGlyphsString.substring(parsePosition));
        break;
      }
      result.append(gfxFontGlyphsString, parsePosition, commentPosition);
      parsePosition = gfxFontGlyphsString.indexOf('\n', commentPosition);
      if (parsePosition == -1)
      {
        // rest of String is within comment
        break;
      }
    }
    gfxFontGlyphsString = result.toString();
  }

  void nextGlyphString()
  {
    int startPosition = gfxFontGlyphsString.indexOf('{', fontGlyphStringParsePosition);
    if (startPosition == -1)
    {
      currentGlyphString = null;
      return;
    }
    int endPosition = gfxFontGlyphsString.indexOf('}', startPosition);
    currentGlyphString = gfxFontGlyphsString.substring(startPosition + 1, endPosition);
    fontGlyphStringParsePosition = endPosition + 1;
  }

  Glyph parseGlyphString()
  {
    Glyph result = new Glyph();
    withinGlyphParsePosition = 0;
    result.bitmapOffset = nextArgumentIntInGlyph();
    result.width = nextArgumentIntInGlyph();
    result.height = nextArgumentIntInGlyph();
    result.xAdvance = nextArgumentIntInGlyph();
    result.xOffset = nextArgumentIntInGlyph();
    result.yOffset = nextArgumentIntInGlyph();

    return result;
  }

  void nextArgumentStringInGlyph()
  {
    int nextCommaPosition = currentGlyphString.indexOf(',', withinGlyphParsePosition);
    if (nextCommaPosition == -1)
    {
      if (withinGlyphParsePosition >= currentGlyphString.length())
      {
        throw new RuntimeException("past end in current glyph " + currentGlyphString);
      }
      nextCommaPosition = currentGlyphString.length();
    }
    withinGlyphCurrentArgument
        = currentGlyphString.substring(withinGlyphParsePosition, nextCommaPosition);
    withinGlyphParsePosition = nextCommaPosition + 1;
  }

  Integer nextArgumentIntInGlyph()
  {
    nextArgumentStringInGlyph();
    try
    {
      return Integer.parseInt(withinGlyphCurrentArgument.trim());
    }
    catch(NumberFormatException e)
    {
      throw new RuntimeException("Cannot parse Argument " + withinGlyphCurrentArgument
          + " within glyph String " + currentGlyphString);
    }
  }

  public String getGfxFontGlyphsString()
  {
    return gfxFontGlyphsString;
  }

  int getFontGlyphStringParsePosition()
  {
    return fontGlyphStringParsePosition;
  }

  void setFontGlyphStringParsePosition(int fontGlyphStringParsePosition)
  {
    this.fontGlyphStringParsePosition = fontGlyphStringParsePosition;
  }

  String getCurrentGlyphString()
  {
    return currentGlyphString;
  }

  void setCurrentGlyphString(String currentGlyphString)
  {
    this.currentGlyphString = currentGlyphString;
  }

  int getWithinGlyphParsePosition()
  {
    return withinGlyphParsePosition;
  }

  void setWithinGlyphParsePosition(int withinGlyphParsePosition)
  {
    this.withinGlyphParsePosition = withinGlyphParsePosition;
  }

  public String getWithinGlyphCurrentArgument()
  {
    return withinGlyphCurrentArgument;
  }
}
