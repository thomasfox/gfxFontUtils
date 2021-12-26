package com.github.thomasfox.gfxfont;

public class BitmapMirror
{
  final Bitmap originalBitmap;

  public BitmapMirror(Bitmap originalBitmap)
  {
    this.originalBitmap = originalBitmap;
  }

  public Bitmap mirrorX()
  {
    Bitmap result = new Bitmap(originalBitmap.getWidth(), originalBitmap.getHeight());
    for (int x = 0; x < originalBitmap.getWidth(); x++)
    {
      for (int y = 0; y < originalBitmap.getHeight(); y++)
      {
        result.setPixel(originalBitmap.getWidth() - 1 - x, y, originalBitmap.getBitmap()[x][y]);
      }
    }
    return result;
  }

  public Bitmap mirrorY()
  {
    Bitmap result = new Bitmap(originalBitmap.getWidth(), originalBitmap.getHeight());
    for (int x = 0; x < originalBitmap.getWidth(); x++)
    {
      for (int y = 0; y < originalBitmap.getHeight(); y++)
      {
        result.setPixel(x, originalBitmap.getHeight() - 1 - y, originalBitmap.getPixel(x, y));
      }
    }
    return result;
  }
}
