package com.github.thomasfox.gfxfont.main;

import com.github.thomasfox.gfxfont.Bitmap;
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

    Bitmap bitmap = new BitmapClipper(font.asBitmap(toPrint)).clipWhitespace();

    System.out.println("\nNot modified:\n=============\n");
    print(bitmap);

    System.out.println("\nRotated right:\n==============\n");
    Bitmap rotatedRightBitmap = new BitmapRotator(bitmap).rotateRight();
    print(rotatedRightBitmap);

    System.out.println("\nMirrored X:\n==========\n");
    Bitmap mirroredXBitmap = new BitmapMirror(bitmap).mirrorX();
    print(mirroredXBitmap);

    System.out.println("\nMirrored Y:\n==========\n");
    Bitmap mirroredYBitmap = new BitmapMirror(bitmap).mirrorY();
    print(mirroredYBitmap);
  }

  private static void print(Bitmap bitmap)
  {
    System.out.println("width: " + bitmap.getWidth() + "px, height: " + bitmap.getHeight() + " px\n");
    BitmapPrinter bitmapPrinter = new BitmapPrinter(bitmap);
    bitmapPrinter.printBitmap(System.out);

    System.out.println("\n## GFX-Style Hex:\n");
    bitmapPrinter.printBitmapAsHex(System.out);

    System.out.println("\n## DOG-Style Hex:\n");
    bitmapPrinter = new BitmapPrinter(new BitmapClipper(bitmap).addWhitespaceForHeightMultipleOfEight());
    bitmapPrinter.printBitmapAsHexInDogFormat(System.out);
    System.out.println();
  }
}
