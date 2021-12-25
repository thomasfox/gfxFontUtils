package com.github.thomasfox.gfxfont;

import java.util.ArrayList;
import java.util.List;

class GfxFontBitmapParser
{
  private CommaSeparatedStringParser bitmapsStringParser;

  public GfxFontBitmapParser(String gfxFontBitmapsString)
  {
    bitmapsStringParser = new CommaSeparatedStringParser(gfxFontBitmapsString);
  }

  byte[] parse()
  {
    List<Byte> resultList = new ArrayList<>();
    Byte next;
    while ((next = bitmapsStringParser.nextArgumentByteOrNull()) != null)
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
}
