package com.github.thomasfox.gfxfont.parse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.github.thomasfox.gfxfont.Bitmap;
import com.github.thomasfox.gfxfont.Glyph;

class GfxGlyphParserTest
{
  private GfxGlyphParser gfxGlyphParser;

  @BeforeEach
  void beforeEach()
  {
    gfxGlyphParser = new GfxGlyphParser("", "");
  }

  @Test
  void fillBitmapIoGlyph()
  {
    // arrange
    gfxGlyphParser = new GfxGlyphParser(
        "0x00,0xA0",
        "{1, 2, 2, 0, 0, 0}");
    gfxGlyphParser.setGfxFontBitmap(new byte[] {0x00, (byte) 0xA0});
    Glyph glyph = new Glyph();
    glyph.bitmapOffset = 1;
    glyph.bitmap = new Bitmap(new boolean[2][2]);
    boolean[][] bitmap;      // bitmap for the glyph, first index is X, second index is Y

    // act
    gfxGlyphParser.fillBitmapIoGlyph(glyph);

    // assert
    assertThat(glyph.bitmap.getBitmap()).isEqualTo(new boolean[][] {{true, true}, {false, false}});
  }

  @Test
  void fillBitmapIoGlyph_pastByteBoundary()
  {
    // arrange
    gfxGlyphParser = new GfxGlyphParser(
        "0x00,0xFE,0x00",
        "{1, 2, 6, 0, 0, 0}");
    gfxGlyphParser.setGfxFontBitmap(new byte[]{0x00, (byte) 0xFE, 0x00});
    Glyph glyph = new Glyph();
    glyph.bitmapOffset = 1;
    glyph.bitmap = new Bitmap(new boolean[2][6]);

    // act
    gfxGlyphParser.fillBitmapIoGlyph(glyph);

    // assert
    assertThat(glyph.bitmap.getBitmap()).isEqualTo(new boolean[][] {
        {true, true, true, true, false, false},
        {true, true, true, false, false, false}});
  }

  @Test
  void parse()
  {
    gfxGlyphParser = new GfxGlyphParser(
        "0x00, 0xFE, 0x00",
        "{0,2,2,11,0,1},\n{1, 2, 6, 11, 5, -10}");

    // act
    List<Glyph> result = gfxGlyphParser.parse();

    // assert
    assertThat(result.get(0).bitmapOffset).isEqualTo(0);
    assertThat(result.get(0).bitmap.getWidth()).isEqualTo(2);
    assertThat(result.get(0).bitmap.getHeight()).isEqualTo(2);
    assertThat(result.get(0).xAdvance).isEqualTo(11);
    assertThat(result.get(0).xOffset).isEqualTo(0);
    assertThat(result.get(0).yOffset).isEqualTo(1);
    assertThat(result.get(0).bitmap.getBitmap()).isEqualTo(new boolean[][] {{false, false}, {false, false}});

    assertThat(result.get(1).bitmapOffset).isEqualTo(1);
    assertThat(result.get(1).xAdvance).isEqualTo(11);
    assertThat(result.get(1).xOffset).isEqualTo(5);
    assertThat(result.get(1).yOffset).isEqualTo(-10);
    assertThat(result.get(1).bitmap.getWidth()).isEqualTo(2);
    assertThat(result.get(1).bitmap.getHeight()).isEqualTo(6);
    assertThat(result.get(1).bitmap.getBitmap()).isEqualTo(new boolean[][] {
        {true, true, true, true, false, false},
        {true, true, true, false, false, false}});
  }
}