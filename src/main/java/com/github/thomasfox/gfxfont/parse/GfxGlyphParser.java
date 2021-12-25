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
      addBitmapToGlyph(glyph);
    }

    return result;
  }

  void addBitmapToGlyph(Glyph glyph)
  {
    boolean[][] bitmap = new boolean[glyph.width][glyph.height];
    int bitmapOffsetBytePart = glyph.bitmapOffset;
    int mask = 0x80;
    for (int y = 0; y < glyph.height; y++)
    {
      for (int x = 0; x < glyph.width; x++)
      {
        byte bitmapByte = gfxFontBitmap[bitmapOffsetBytePart];
        bitmap[x][y] = (bitmapByte & mask) != 0;
        mask >>= 1;
        if (mask == 0)
        {
          mask = 0x80;
          bitmapOffsetBytePart++;
        }
      }
    }
    glyph.bitmap =  bitmap;
  }

  void setGfxFontBitmap(byte[] gfxFontBitmap)
  {
    this.gfxFontBitmap = gfxFontBitmap;
  }

}
