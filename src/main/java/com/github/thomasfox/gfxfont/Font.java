package com.github.thomasfox.gfxfont;

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
}
