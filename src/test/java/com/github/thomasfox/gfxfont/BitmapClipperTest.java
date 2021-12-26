package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BitmapClipperTest
{
  private BitmapClipper bitmapClipper;

  @BeforeEach
  void createBitmapClipper()
  {
    bitmapClipper = new BitmapClipper(new Bitmap( new boolean[][] {
        {false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false},
        {false, false, false, true, false, false, false},
        {false, false, false, false, true, false, false},
        {false, false, false, false, false, false, false}}));
  }

  @Test
  void getXStart()
  {
    assertThat(bitmapClipper.getXStart()).isEqualTo(2);
  }

  @Test
  void getXEnd()
  {
    assertThat(bitmapClipper.getXEnd()).isEqualTo(3);
  }

  @Test
  void getYStart()
  {
    assertThat(bitmapClipper.getYStart()).isEqualTo(3);
  }

  @Test
  void getYEnd()
  {
    assertThat(bitmapClipper.getYEnd()).isEqualTo(4);
  }

  @Test
  void clipWhitespace()
  {
    assertThat(bitmapClipper.clipWhitespace()).isEqualTo(new Bitmap(new boolean[][] {
        {true, false},
        {false, true}}));
  }

  @Test
  void addWhitespaceForHeightMultipleOfEight()
  {
    assertThat(bitmapClipper.addWhitespaceForHeightMultipleOfEight()).isEqualTo(new Bitmap(new boolean[][] {
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, true, false, false, false, false},
        {false, false, false, false, true, false, false, false},
        {false, false, false, false, false, false, false, false}}));
  }
}