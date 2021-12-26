package com.github.thomasfox.gfxfont;

public class BitmapRotator
{
  final boolean[][] originalBitmap; // first index is X, second index is Y

  final int width;
  final int height;

  public BitmapRotator(boolean[][] originalBitmap)
  {
    this.originalBitmap = originalBitmap;
    width = originalBitmap.length;
    height = originalBitmap[0].length;
  }

  public boolean[][] rotateRight()
  {
    boolean[][] result = new boolean[height][width];
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        result[height - 1 - y][x] = originalBitmap[x][y];
      }
    }
    return result;
  }

  int getWidth()
  {
    return width;
  }

  int getHeight()
  {
    return height;
  }
}
