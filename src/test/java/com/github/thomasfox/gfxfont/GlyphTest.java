package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GlyphTest
{
  private Glyph glyph1;

  private Glyph glyph2;

  @BeforeEach
  void fillGlyphs()
  {
    glyph1 = new Glyph();
    glyph1.bitmap = new Bitmap(new boolean[][] {{false, false}, {true, false}, {false, true}});
    glyph1.bitmapOffset = 0;
    glyph1.xAdvance = 3;
    glyph1.xOffset = 4;
    glyph1.yOffset = -10;

    glyph2 = new Glyph();
    glyph2.bitmap = new Bitmap(new boolean[][] {{false, false}, {true, false}, {false, true}});
    glyph2.bitmapOffset = 0;
    glyph2.xAdvance = 3;
    glyph2.xOffset = 4;
    glyph2.yOffset = -10;
  }

  @Test
  void testEquals_same()
  {
    assertThat(glyph1.equals(glyph1)).isTrue();
  }

  @Test
  void testEquals_null()
  {
    assertThat(glyph1.equals(null)).isFalse();
  }

  @Test
  void testOtherClass_null()
  {
    assertThat(glyph1.equals("")).isFalse();
  }

  @Test
  void testEquals_equal()
  {
    assertThat(glyph1.equals(glyph2)).isTrue();
  }

  @Test
  void testEquals_bitmapOffsetNotEqual()
  {
    glyph2.bitmapOffset = 99;
    assertThat(glyph1.equals(glyph2)).isFalse();
  }

  @Test
  void testEquals_xAdvanceNotEqual()
  {
    glyph2.xAdvance = 99;
    assertThat(glyph1.equals(glyph2)).isFalse();
  }

  @Test
  void testEquals_xOffsetNotEqual()
  {
    glyph2.xOffset = 99;
    assertThat(glyph1.equals(glyph2)).isFalse();
  }

  @Test
  void testEquals_yOffsetNotEqual()
  {
    glyph2.yOffset = 99;
    assertThat(glyph1.equals(glyph2)).isFalse();
  }

  @Test
  void testEquals_bitmapNotEqual()
  {
    glyph2.bitmap = new Bitmap(new boolean[][] {{false, false}, {false, false}, {false, false}});
    assertThat(glyph1.equals(glyph2)).isFalse();
  }

  @Test
  void testHashCode_equal()
  {
    assertThat(glyph1.hashCode()).isEqualTo(glyph2.hashCode());
  }
}