package com.github.thomasfox.gfxfont;

public class FontLoaderMain
{
  public static void main(String[] argv)
  {
    String filename = argv[0];
    FontLoader fontLoader = new FontLoader();
    fontLoader.load(filename);
    for(Glyph glyph : fontLoader.getGlyphs())
    {
      glyph.printBitmapToStdOut();
    }
  }
}
