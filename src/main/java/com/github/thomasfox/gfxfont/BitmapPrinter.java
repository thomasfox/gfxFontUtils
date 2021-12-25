package com.github.thomasfox.gfxfont;

import java.io.PrintStream;

public class BitmapPrinter
{
  public static void printBitmap(boolean[][] bitmap, PrintStream out)
  {
    int width = bitmap.length;
    int height = bitmap.length == 0 ? 0 : bitmap[0].length;
    for (int y = 0; y < height; y++)
    {
      for (int x = 0; x < width; x++)
      {
        if (bitmap[x][y])
        {
          out.print('X');
        }
        else
        {
          out.print('.');
        }
      }
      out.print('\n');
    }
    out.print('\n');
  }
}
