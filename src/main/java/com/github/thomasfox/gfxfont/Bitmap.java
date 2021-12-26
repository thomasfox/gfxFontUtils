package com.github.thomasfox.gfxfont;

import java.util.Arrays;

public class Bitmap
{
  private boolean[][] bitmap;  // first index is X, second index is Y

  private int width;           // width in pixels

  private int height;          // height in pixels

  public Bitmap(boolean[][] bitmap)
  {
    this.bitmap = bitmap;
    width = bitmap.length;
    height = bitmap.length == 0 ? 0 : bitmap[0].length;
  }

  public Bitmap(int width, int height)
  {
    this(new boolean[width][height]);
  }

  public boolean[][] getBitmap()
  {
    return bitmap;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  public boolean getPixel(int x, int y)
  {
    return bitmap[x][y];
  }

  public void setPixel(int x, int y, boolean value)
  {
    bitmap[x][y] = value;
  }


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
    Bitmap bitmap1 = (Bitmap) o;
    return Arrays.deepEquals(bitmap, bitmap1.bitmap);
  }

  @Override
  public int hashCode()
  {
    return Arrays.deepHashCode(bitmap);
  }
}
