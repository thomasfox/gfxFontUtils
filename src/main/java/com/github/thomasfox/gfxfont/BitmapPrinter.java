package com.github.thomasfox.gfxfont;

import java.io.PrintStream;

import com.github.thomasfox.gfxfont.parse.ByteParser;

public class BitmapPrinter
{
  private boolean[][] bitmap;

  private int width;

  private int height;

  private int byteCountInLine;

  private boolean firstPrintedHex;


  public BitmapPrinter(boolean[][] bitmap)
  {
    this.bitmap = bitmap;
    width = bitmap.length;
    height = bitmap.length == 0 ? 0 : bitmap[0].length;
  }

  public void printBitmap(PrintStream out)
  {
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

  public void printBitmapAsHex(PrintStream out)
  {
    int mask = 0x80;
    byte currentByte = 0x00;
    byteCountInLine = 0;
    firstPrintedHex = true;
    for (int y = 0; y < height; y++)
    {
      for (int x = 0; x < width; x++)
      {
        if (bitmap[x][y])
        {
          currentByte |= mask;
        }
        mask >>= 1;
        if (mask == 0)
        {
          printCommaAndLineBreakIfNecessary(out);
          out.print(ByteParser.toHexString(currentByte));

          mask = 0x80;
          currentByte = 0x00;
        }
      }
    }
    if (mask != 0x80)
    {
      printCommaAndLineBreakIfNecessary(out);
      out.print(ByteParser.toHexString(currentByte));
    }
    out.print('\n');
  }

  private void printCommaAndLineBreakIfNecessary(PrintStream out)
  {
    if (!firstPrintedHex)
    {
      out.print(", ");
      byteCountInLine++;
      if (byteCountInLine >= 10)
      {
        out.print('\n');
        byteCountInLine = 0;
      }
    }
    firstPrintedHex = false;
  }
}
