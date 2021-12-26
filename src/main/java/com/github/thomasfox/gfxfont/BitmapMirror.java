package com.github.thomasfox.gfxfont;

public class BitmapMirror
{
  final boolean[][] originalBitmap; // first index is X, second index is Y

  final int width;
  final int height;

  public BitmapMirror(boolean[][] originalBitmap)
  {
    this.originalBitmap = originalBitmap;
    width = originalBitmap.length;
    height = originalBitmap[0].length;
  }

  public boolean[][] mirrorX()
  {
    boolean[][] result = new boolean[width][height];
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        result[width - 1 - x][y] = originalBitmap[x][y];
      }
    }
    return result;
  }

  public boolean[][] mirrorY()
  {
    boolean[][] result = new boolean[width][height];
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        result[x][height - 1 - y] = originalBitmap[x][y];
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
