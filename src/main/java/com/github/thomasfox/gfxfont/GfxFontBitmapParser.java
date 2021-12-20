package com.github.thomasfox.gfxfont;

import java.util.ArrayList;
import java.util.List;

class GfxFontBitmapParser
{
  private String gfxFontBitmapsString;

  private int fontBitmapsStringParsePosition = 0;

  public GfxFontBitmapParser(String gfxFontBitmapsString)
  {
    this.gfxFontBitmapsString = gfxFontBitmapsString;
  }

  byte[] parse()
  {
    List<Byte> resultList = new ArrayList<>();
    Byte next;
    while ((next = nextArgumentInFontBitmapsString()) != null)
    {
      resultList.add(next);
    }
    byte[] result = new byte[resultList.size()];
    for (int i = 0; i < resultList.size(); i++)
    {
      result[i] = resultList.get(i);
    }
    return result;
  }

  Byte nextArgumentInFontBitmapsString()
  {
    int nextCommaPosition = gfxFontBitmapsString.indexOf(',', fontBitmapsStringParsePosition);
    if (nextCommaPosition == -1)
    {
      if (fontBitmapsStringParsePosition >= gfxFontBitmapsString.length())
      {
        return null;
      }
      nextCommaPosition = gfxFontBitmapsString.length();
    }
    String argumentString = gfxFontBitmapsString.substring(
        fontBitmapsStringParsePosition,
        nextCommaPosition);
    Byte result = (byte) (Integer.decode(argumentString.trim()) & 0xFF);
    fontBitmapsStringParsePosition = nextCommaPosition + 1;
    return result;
  }

  public int getFontBitmapsStringParsePosition()
  {
    return fontBitmapsStringParsePosition;
  }

  public void setFontBitmapsStringParsePosition(int fontBitmapsStringParsePosition)
  {
    this.fontBitmapsStringParsePosition = fontBitmapsStringParsePosition;
  }
}
