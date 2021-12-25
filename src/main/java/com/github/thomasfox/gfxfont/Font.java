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
    int length = 0;
    byte[] isoBytesForString = toPrint.getBytes(StandardCharsets.ISO_8859_1);
    for (byte b : isoBytesForString)
    {
      Glyph glyph = getGlyph(b);
      length += glyph.xAdvance;
    }
    boolean[][] result = new boolean[length][lineHeight];

    int charOffset = 0;
    for (byte b : isoBytesForString)
    {
      Glyph glyph = getGlyph(b);
      int x = 0;
      for (boolean[] xBitmap : glyph.bitmap)
      {
        int y = 0;
        for (boolean pixel : xBitmap)
        {
          result[x + charOffset][y] = pixel;
          y++;
        }
        x++;
      }
      charOffset += glyph.xAdvance;
    }
    return result;
  }
}
