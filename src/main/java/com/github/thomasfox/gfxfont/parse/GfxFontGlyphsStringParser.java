package com.github.thomasfox.gfxfont.parse;

import java.util.ArrayList;
import java.util.List;

import com.github.thomasfox.gfxfont.Bitmap;
import com.github.thomasfox.gfxfont.Glyph;

class GfxFontGlyphsStringParser
{
  private String gfxFontGlyphsString;

  private int fontGlyphStringParsePosition = 0;
  private String currentGlyphString = null;

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
    CommaSeparatedStringParser withingGlyphStringParser = new CommaSeparatedStringParser(currentGlyphString);
    result.bitmapOffset = withingGlyphStringParser.nextArgumentIntNotNull();
    int width = withingGlyphStringParser.nextArgumentIntNotNull();
    int height = withingGlyphStringParser.nextArgumentIntNotNull();
    result.bitmap = new Bitmap(width, height);
    result.xAdvance = withingGlyphStringParser.nextArgumentIntNotNull();
    result.xOffset = withingGlyphStringParser.nextArgumentIntNotNull();
    result.yOffset = withingGlyphStringParser.nextArgumentIntNotNull();

    return result;
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
}
