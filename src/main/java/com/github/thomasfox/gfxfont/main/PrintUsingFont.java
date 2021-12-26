package com.github.thomasfox.gfxfont.main;

import com.github.thomasfox.gfxfont.BitmapClipper;
import com.github.thomasfox.gfxfont.BitmapMirror;
import com.github.thomasfox.gfxfont.BitmapPrinter;
import com.github.thomasfox.gfxfont.BitmapRotator;
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

    System.out.println("\nNot modified:\n=============\n");
    print(bitmap);

    System.out.println("\nRotated right:\n==============\n");
    boolean[][] rotatedRightBitmap = new BitmapRotator(bitmap).rotateRight();
    print(rotatedRightBitmap);

    System.out.println("\nMirrored X:\n==========\n");
    boolean[][] mirroredXBitmap = new BitmapMirror(bitmap).mirrorX();
    print(mirroredXBitmap);

    System.out.println("\nMirrored Y:\n==========\n");
    boolean[][] mirroredYBitmap = new BitmapMirror(bitmap).mirrorY();
    print(mirroredYBitmap);
  }

  private static void print(boolean[][] bitmap)
  {
    System.out.println("width: " + bitmap.length + "px, height: " + bitmap[0].length + " px\n");
    BitmapPrinter bitmapPrinter = new BitmapPrinter(bitmap);
    bitmapPrinter.printBitmap(System.out);
    bitmapPrinter.printBitmapAsHex(System.out);
  }
}
