package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BitmapTest
{
  private Bitmap bitmap1;

  private Bitmap bitmap2;

  private Bitmap emptyBitmap;

  @BeforeEach
  void createBitmap()
  {
    bitmap1 = new Bitmap(new boolean[][] {
        {false, false},
        {false, true},
        {true, false}});
    bitmap2 = new Bitmap(new boolean[][] {
        {false, false},
        {false, true},
        {true, false}});
    emptyBitmap = new Bitmap(3, 2);
  }

  @Test
  void getWidth_constructor1()
  {
    assertThat(bitmap1.getWidth()).isEqualTo(3);
  }

  @Test
  void getWidth_constructor2()
  {
    assertThat(emptyBitmap.getWidth()).isEqualTo(3);
  }

  @Test
  void getHeight_constructor1()
  {
    assertThat(bitmap1.getHeight()).isEqualTo(2);
  }

  @Test
  void getHeight_constructor2()
  {
    assertThat(emptyBitmap.getHeight()).isEqualTo(2);
  }

  @Test
  void getPixel_constructor1()
  {
    assertThat(bitmap1.getPixel(2,0)).isTrue();
  }

  @Test
  void getPixel_constructor2()
  {
    assertThat(emptyBitmap.getPixel(2,1)).isFalse();
  }

  @Test
  void setPixel()
  {
    // assert arrangement
    assertThat(bitmap1.getPixel(0,1)).isFalse();

    // act
    bitmap1.setPixel(0, 1, true);

    // assert
    assertThat(bitmap1.getPixel(0,1)).isTrue();
  }

  @Test
  void testEquals_same()
  {
    assertThat(bitmap1.equals(bitmap1)).isTrue();
  }

  @Test
  void testEquals_null()
  {
    assertThat(bitmap1.equals(null)).isFalse();
  }

  @Test
  void testOtherClass_null()
  {
    assertThat(bitmap1.equals("")).isFalse();
  }

  @Test
  void testEquals_equal()
  {
    assertThat(bitmap1.equals(bitmap2)).isTrue();
  }

  @Test
  void testEquals_bitmapNotEqual()
  {
    assertThat(bitmap1.equals(emptyBitmap)).isFalse();
  }

  @Test
  void testHashCode_equal()
  {
    assertThat(bitmap1.hashCode()).isEqualTo(bitmap2.hashCode());
  }
}