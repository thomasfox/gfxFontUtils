package com.github.thomasfox.gfxfont;

public class BitmapRotator
{
  final Bitmap originalBitmap;

  public BitmapRotator(Bitmap originalBitmap)
  {
    this.originalBitmap = originalBitmap;
  }

  public Bitmap rotateRight()
  {
    Bitmap result = new Bitmap(originalBitmap.getHeight(), originalBitmap.getWidth());
    for (int x = 0; x < originalBitmap.getWidth(); x++)
    {
      for (int y = 0; y < originalBitmap.getHeight(); y++)
      {
        result.setPixel(originalBitmap.getHeight() - 1 - y, x, originalBitmap.getPixel(x, y));
      }
    }
    return result;
  }
}
