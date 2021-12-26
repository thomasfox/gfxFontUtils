package com.github.thomasfox.gfxfont;

import java.io.PrintStream;

import com.github.thomasfox.gfxfont.parse.ByteParser;

public class BitmapPrinter
{
  private Bitmap bitmap;

  private int byteCountInLine;

  private boolean firstPrintedHex;

  public BitmapPrinter(Bitmap bitmap)
  {
    this.bitmap = bitmap;
  }

  public void printBitmap(PrintStream out)
  {
    for (int y = 0; y < bitmap.getHeight(); y++)
    {
      for (int x = 0; x < bitmap.getWidth(); x++)
      {
        if (bitmap.getPixel(x, y))
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
    for (int y = 0; y < bitmap.getHeight(); y++)
    {
      for (int x = 0; x < bitmap.getWidth(); x++)
      {
        if (bitmap.getPixel(x, y))
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

  /**
   * Prints the bitmap in a format which is convenient for handling
   * in Electronic Assembly's DOG Display series.
   *
   * The DOG format is such that each x row contains 8 vertical pixels.
   * LSB of each byte is the uppermost pixel.
   * This method prints the x rows as separate arrays.
   *
   * This method only accepts bitmaps which have a height which is a multiple of eight.
   */
  public void printBitmapAsHexInDogFormat(PrintStream out)
  {
    if ((bitmap.getHeight() % 8) != 0)
    {
      throw new IllegalStateException("Height must be a multiple of eight");
    }

    firstPrintedHex = true;
    byteCountInLine = 0;
    for (int yByte = 0; yByte < bitmap.getHeight() / 8; yByte++)
    {
      for (int x = 0; x < bitmap.getWidth(); x++)
      {
        byte mask = 0x01;
        byte currentByte = 0;
        for (int yBit = 0; yBit < 8; yBit++)
        {
          if (bitmap.getPixel(x, yByte * 8 + yBit))
          {
            currentByte += mask;
          }
          mask <<= 1;
        }
        printCommaAndLineBreakIfNecessary(out);
        out.print(ByteParser.toHexString(currentByte));
      }
    }
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
