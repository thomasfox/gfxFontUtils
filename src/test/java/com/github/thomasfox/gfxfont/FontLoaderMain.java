package com.github.thomasfox.gfxfont;

public class FontLoaderMain
{
  public static void main(String[] argv)
  {
    String filename = argv[0];
    FontLoader fontLoader = new FontLoader();
    Font font = fontLoader.load(filename);
    for (Glyph glyph : font.getGlyphList())
    {
      glyph.printBitmapToStdOut();
    }
  }
}
