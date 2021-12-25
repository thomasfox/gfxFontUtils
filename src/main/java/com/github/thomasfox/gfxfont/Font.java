package com.github.thomasfox.gfxfont;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Font
{
  /** The character definitions in this font.
   * The first glyph is the definition for <code>firstChar</code>,
   * the second for <code>firstChar + 1</code> and so on
   * until the last glyph which defines <code>lastChar</code>.
   */
  private List<Glyph> glyphList;

  /** The first character of the range of characters contained in this font. */
  private byte firstChar;

  /** The last character of the range of characters contained in this font. */
  private byte lastChar;

  /** The distance between two lines. */
  private int lineHeight;

  public List<Glyph> getGlyphList()
  {
    return glyphList;
  }

  public void setGlyphList(List<Glyph> glyphList)
  {
    this.glyphList = glyphList;
  }

  public byte getFirstChar()
  {
    return firstChar;
  }

  public void setFirstChar(byte firstChar)
  {
    this.firstChar = firstChar;
  }

  public byte getLastChar()
  {
    return lastChar;
  }

  public void setLastChar(byte lastChar)
  {
    this.lastChar = lastChar;
  }

  public int getLineHeight()
  {
    return lineHeight;
  }

  public void setLineHeight(int lineHeight)
  {
    this.lineHeight = lineHeight;
  }

  public Glyph getGlyph(byte glyphKey)
  {
    if (glyphKey < firstChar || glyphKey > lastChar)
    {
      return null;
    }
    return glyphList.get(glyphKey - firstChar);
  }

  byte[] getBytes(String toConvert)
  {
    return toConvert.getBytes(StandardCharsets.ISO_8859_1);
  }

  int getPixelWidth(String toPrint)
  {
    int width = 0;
    byte[] isoBytesForString = getBytes(toPrint);
    for (byte b : isoBytesForString)
    {
      Glyph glyph = getGlyph(b);
      width += glyph.xAdvance;
    }
    return width;
  }

  /**
   * Prints a string into a bitmap using the glyph bitmaps in the font.
   * Currently fails if the passed string contains characters unknown to the font.
   *
   * @param toPrint the string to print as bitmap, not null
   *
   * @return the bitmap for the String.
   */
  public boolean[][] asBitmap(String toPrint)
  {
    int width = getPixelWidth(toPrint);
    boolean[][] result = new boolean[width][lineHeight];

    int baseline = getBaseline();

    int charXOffset = 0;
    byte[] isoBytesForString = getBytes(toPrint);
    for (byte b : isoBytesForString)
    {
      Glyph glyph = getGlyph(b);
      int x = 0;
      for (boolean[] xBitmap : glyph.bitmap)
      {
        int y = 0;
        for (boolean pixel : xBitmap)
        {
          result[x + charXOffset + glyph.xOffset][y + baseline + glyph.yOffset] = pixel;
          y++;
        }
        x++;
      }
      charXOffset += glyph.xAdvance;
    }
    return result;
  }

  /**
   * In gfxfonts, the pixel coordinates are given with respect to a virtual baseline
   * (the lowest pixel of a character without descender like e.g. "a").
   * But the font contains no number to tell how much the baseline must be below the uppermost line
   * in the display so that all characters in the font fit in the display area.
   *
   * This method determines the smallest possible value for the baseline y so that all characters
   * in the font have non-negative y coordinates.
   *
   * @return the smallest possible y baseline fitting for all characters in this font.
   */
  public int getBaseline()
  {
    int baseline = 0;
    for (Glyph glyph : glyphList)
    {
      if (-glyph.yOffset > baseline)
      {
        baseline = -glyph.yOffset;
      }
    }
    return baseline;
  }
}
