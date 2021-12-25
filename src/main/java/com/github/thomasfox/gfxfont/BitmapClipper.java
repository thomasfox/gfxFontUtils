package com.github.thomasfox.gfxfont;

public class BitmapClipper
{
  final boolean[][] originalBitmap; // first index is X, second index is Y

  final int width;
  final int height;

  public BitmapClipper(boolean[][] originalBitmap)
  {
    this.originalBitmap = originalBitmap;
    width = originalBitmap.length;
    height = originalBitmap[0].length;
  }

  public boolean[][] clipWhitespace()
  {
    int xStart = getXStart();
    int xEnd = getXEnd();
    int yStart = getYStart();
    int yEnd = getYEnd();
    boolean[][] result = new boolean[xEnd - xStart + 1][yEnd - yStart + 1];
    for (int x = xStart; x <= xEnd; x++)
    {
      for (int y = yStart; y <= yEnd; y++)
      {
        result[x - xStart][y - yStart] = originalBitmap[x][y];
      }
    }
    return result;
  }

  int getXStart()
  {
    int xStart = 0;
    while (xStart < width)
    {
      boolean foundPixel = false;
      for (int y = 0; y < height; y++)
      {
        foundPixel |= originalBitmap[xStart][y];
      }
      if (foundPixel)
      {
        break;
      }
      xStart++;
    }
    return xStart;
  }

  int getXEnd()
  {
    int xEnd = width - 1;
    while (xEnd > 0)
    {
      boolean foundPixel = false;
      for (int y = 0; y < height; y++)
      {
        foundPixel |= originalBitmap[xEnd][y];
      }
      if (foundPixel)
      {
        break;
      }
      xEnd--;
    }
    return xEnd;
  }


  int getYStart()
  {
    int yStart = 0;
    while (yStart < height)
    {
      boolean foundPixel = false;
      for (int x = 0; x < width; x++)
      {
        foundPixel |= originalBitmap[x][yStart];
      }
      if (foundPixel)
      {
        break;
      }
      yStart++;
    }
    return yStart;
  }

  int getYEnd()
  {
    int yEnd = height - 1;
    while (yEnd > 0)
    {
      boolean foundPixel = false;
      for (int x = 0; x < width; x++)
      {
        foundPixel |= originalBitmap[x][yEnd];
      }
      if (foundPixel)
      {
        break;
      }
      yEnd--;
    }
    return yEnd;
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
