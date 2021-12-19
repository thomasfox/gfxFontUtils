package com.github.thomasfox.gfxfont;

import java.util.ArrayList;
import java.util.List;

public class GfxGlyphParser
{
  private String gfxFontBitmapsString;
  private String gfxFontGlyphsString;

  private int fontGlyphStringParsePosition = -1;
  private String currentGlyphString = null;
  private int withinGlyphParsePosition;
  private String withinGlyphCurrentArgument;

  public GfxGlyphParser(String gfxFontBitmapsString, String gfxFontGlyphsString)
  {
    this.gfxFontBitmapsString = gfxFontBitmapsString;
    this.gfxFontGlyphsString = gfxFontGlyphsString;
  }

  public List<Glyph> parse()
  {
    List<Glyph> result = new ArrayList<>();

    while (true)
    {
      nextGlyphString();
      if (currentGlyphString == null)
      {
        break;
      }
      result.add(parseGlyphString());
    }

    return result;
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
    withinGlyphParsePosition = -1;
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
        = currentGlyphString.substring(withinGlyphParsePosition + 1, nextCommaPosition);
    withinGlyphParsePosition = nextCommaPosition + 1;
  }

  Integer nextArgumentIntInGlyph()
  {
    nextArgumentStringInGlyph();
    return Integer.parseInt(withinGlyphCurrentArgument);
  }

  // state access for tests in methods below

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

  public void setWithinGlyphCurrentArgument(String withinGlyphCurrentArgument)
  {
    this.withinGlyphCurrentArgument = withinGlyphCurrentArgument;
  }
}
