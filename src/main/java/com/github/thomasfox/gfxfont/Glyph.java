package com.github.thomasfox.gfxfont;

import java.util.Arrays;
import java.util.Objects;

public class Glyph
{
  public int bitmapOffset; // Offset of bitmap for this glyph in bytes in the global bitmap array
  public int xAdvance;     // Distance to advance cursor in X direction after rendering this glyph
  public int xOffset;      // X distance from cursor position to upper left corner
  public int yOffset;      // Y distance from cursor position to upper left corner
  public Bitmap bitmap;    // bitmap for the glyph, first index is X, second index is Y

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
        && xAdvance == glyph.xAdvance
        && xOffset == glyph.xOffset
        && yOffset == glyph.yOffset
        && bitmap.equals(glyph.bitmap);
  }

  @Override
  public int hashCode()
  {
    int result = Objects.hash(bitmapOffset, xAdvance, xOffset, yOffset);
    result = 31 * result + bitmap.hashCode();
    return result;
  }
}
