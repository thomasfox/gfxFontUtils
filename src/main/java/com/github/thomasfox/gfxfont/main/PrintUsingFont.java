package com.github.thomasfox.gfxfont.main;

import com.github.thomasfox.gfxfont.BitmapClipper;
import com.github.thomasfox.gfxfont.BitmapPrinter;
import com.github.thomasfox.gfxfont.Font;
import com.github.thomasfox.gfxfont.FontLoader;

public class PrintUsingFont
{
  public static void main(String[] argv)
  {
    if (argv == null || argv.length != 2)
    {
      System.out.println("USAGE: PrintUsingFont ${pathToFont} ${stringToPrint}");
      return;
    }
    String filename = argv[0];
    FontLoader fontLoader = new FontLoader();
    Font font = fontLoader.load(filename);
    String toPrint = argv[1];
    boolean[][] bitmap = new BitmapClipper(font.asBitmap(toPrint)).clipWhitespace();
    BitmapPrinter bitmapPrinter = new BitmapPrinter(bitmap);
    bitmapPrinter.printBitmap(System.out);
    bitmapPrinter.printBitmapAsHex(System.out);
  }
}
