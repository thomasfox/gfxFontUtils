gfxFontUtils
============

Utilities to work with the fonts from the
[adafruit gfx library](https://github.com/adafruit/Adafruit-GFX-Library).

Currently, this library can
* parse gfxFont files using the [FontParser](src/main/java/com/github/thomasfox/gfxfont/parse/FontParser.java) class 
  into a list of [Glyph](src/main/java/com/github/thomasfox/gfxfont/Glyph.java)s, which contain a bitmap 
  and the offsets for the bitmaps for te characters in the font.
* rotate, mirror and clip whitespace in [Glyph](src/main/java/com/github/thomasfox/gfxfont/Glyph.java)s using the 
  [BitmapRotator](src/main/java/com/github/thomasfox/gfxfont/BitmapRotator.java), 
  [BitmapMirror](src/main/java/com/github/thomasfox/gfxfont/BitmapMirror.java) and 
  [BitmapClipper](src/main/java/com/github/thomasfox/gfxfont/BitmapClipper.java) classes.
* render a String using the bitmaps in the GFX font and print the resulting bitmap
  in a format favorable for the [DOG Graphic LCD Dispays](https://www.lcd-module.com/produkte/dog.html)
  using the [PrintUsingFont](src/main/java/com/github/thomasfox/gfxfont/main/PrintUsingFont.java) class.
  
GFX font files to use with this library can be downloaded from
the [Fonts directory of the gfx library](https://github.com/adafruit/Adafruit-GFX-Library/tree/master/Fonts)
or created frim TTF fonts by the fontconvert utility also contained
in the [gfx library](https://github.com/adafruit/Adafruit-GFX-Library).