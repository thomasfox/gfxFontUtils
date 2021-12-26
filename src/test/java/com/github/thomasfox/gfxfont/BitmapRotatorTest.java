package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BitmapRotatorTest
{
  private BitmapRotator bitmapRotator;

  @BeforeEach
  void createBitmapRotator()
  {
    bitmapRotator = new BitmapRotator(new Bitmap(new boolean[][] {
        {false, true, false},
        {false, false, true}}));
  }

  @Test
  void rotateRight()
  {
    assertThat(bitmapRotator.rotateRight()).isEqualTo(new Bitmap(new boolean[][] {
        {false, true},
        {true, false},
        {false, false}}));
  }
}