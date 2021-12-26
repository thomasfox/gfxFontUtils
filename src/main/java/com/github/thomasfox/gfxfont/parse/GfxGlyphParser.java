package com.github.thomasfox.gfxfont.parse;

import java.util.List;

import com.github.thomasfox.gfxfont.Glyph;

public class GfxGlyphParser
{
  private byte[] gfxFontBitmap;

  private final GfxFontBitmapParser gfxFontBitmapParser;

  private final GfxFontGlyphsStringParser gfxFontGlyphsStringParser;

  public GfxGlyphParser(String gfxFontBitmapsString, String gfxFontGlyphsString)
  {
    gfxFontBitmapParser = new GfxFontBitmapParser(gfxFontBitmapsString);
    gfxFontGlyphsStringParser = new GfxFontGlyphsStringParser(gfxFontGlyphsString);
  }

  public List<Glyph> parse()
  {
    gfxFontBitmap = gfxFontBitmapParser.parse();

    List<Glyph> result = gfxFontGlyphsStringParser.parse();
    for (Glyph glyph : result)
    {
      fillBitmapIoGlyph(glyph);
    }

    return result;
  }

  void fillBitmapIoGlyph(Glyph glyph)
  {
    int bitmapOffsetBytePart = glyph.bitmapOffset;
    int mask = 0x80;
    for (int y = 0; y < glyph.bitmap.getHeight(); y++)
    {
      for (int x = 0; x < glyph.bitmap.getWidth(); x++)
      {
        byte bitmapByte = gfxFontBitmap[bitmapOffsetBytePart];
        glyph.bitmap.getBitmap()[x][y] = (bitmapByte & mask) != 0;
        mask >>= 1;
        if (mask == 0)
        {
          mask = 0x80;
          bitmapOffsetBytePart++;
        }
      }
    }
  }

  void setGfxFontBitmap(byte[] gfxFontBitmap)
  {
    this.gfxFontBitmap = gfxFontBitmap;
  }

}
