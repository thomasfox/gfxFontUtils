package com.github.thomasfox.gfxfont;

public class BitmapClipper
{
  final Bitmap originalBitmap;

  public BitmapClipper(Bitmap originalBitmap)
  {
    this.originalBitmap = originalBitmap;
  }

  public Bitmap clipWhitespace()
  {
    int xStart = getXStart();
    int xEnd = getXEnd();
    int yStart = getYStart();
    int yEnd = getYEnd();
    Bitmap result = new Bitmap(xEnd - xStart + 1, yEnd - yStart + 1);
    for (int x = xStart; x <= xEnd; x++)
    {
      for (int y = yStart; y <= yEnd; y++)
      {
        result.setPixel(x - xStart, y - yStart, originalBitmap.getPixel(x, y));
      }
    }
    return result;
  }

  int getXStart()
  {
    int xStart = 0;
    while (xStart < originalBitmap.getWidth())
    {
      boolean foundPixel = false;
      for (int y = 0; y < originalBitmap.getHeight(); y++)
      {
        foundPixel |= originalBitmap.getPixel(xStart, y);
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
    int xEnd = originalBitmap.getWidth() - 1;
    while (xEnd > 0)
    {
      boolean foundPixel = false;
      for (int y = 0; y < originalBitmap.getHeight(); y++)
      {
        foundPixel |= originalBitmap.getPixel(xEnd, y);
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
    while (yStart < originalBitmap.getHeight())
    {
      boolean foundPixel = false;
      for (int x = 0; x < originalBitmap.getWidth(); x++)
      {
        foundPixel |= originalBitmap.getPixel(x, yStart);
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
    int yEnd = originalBitmap.getHeight() - 1;
    while (yEnd > 0)
    {
      boolean foundPixel = false;
      for (int x = 0; x < originalBitmap.getWidth(); x++)
      {
        foundPixel |= originalBitmap.getPixel(x, yEnd);
      }
      if (foundPixel)
      {
        break;
      }
      yEnd--;
    }
    return yEnd;
  }
}
