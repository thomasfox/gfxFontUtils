package com.github.thomasfox.gfxfont;

public class Glyph
{
  public int bitmapOffset; // Offset of bitmap for this glyph in the global bitmap array
  public int width;        // Width of the bitmap for this glyph in pixels
  public int height;       // Height of the bitmap for this glyph in pixels
  public int xAdvance;     // Distance to advance cursor in X direction after rendering this glyph
  public int xOffset;      // X distance from cursor position to upper left corner
  public int yOffset;      // Y distance from cursor position to upper left corner
}
