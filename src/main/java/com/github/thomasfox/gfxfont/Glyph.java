package com.github.thomasfox.gfxfont;

import java.util.Arrays;
import java.util.Objects;

public class Glyph
{
  public int bitmapOffset; // Offset of bitmap for this glyph in bytes in the global bitmap array
  public int width;        // Width of the bitmap for this glyph in pixels
  public int height;       // Height of the bitmap for this glyph in pixels
  public int xAdvance;     // Distance to advance cursor in X direction after rendering this glyph
  public int xOffset;      // X distance from cursor position to upper left corner
  public int yOffset;      // Y distance from cursor position to upper left corner
  boolean[][] bitmap;      // bitmap for the glyph, first index is X, second index is Y

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    Glyph glyph = (Glyph) o;
    return bitmapOffset == glyph.bitmapOffset
        && width == glyph.width
        && height == glyph.height
        && xAdvance == glyph.xAdvance
        && xOffset == glyph.xOffset
        && yOffset == glyph.yOffset
        && Arrays.deepEquals(bitmap, glyph.bitmap);
  }

  @Override
  public int hashCode()
  {
    int result = Objects.hash(bitmapOffset, width, height, xAdvance, xOffset, yOffset);
    result = 31 * result + Arrays.deepHashCode(bitmap);
    return result;
  }
}
