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
    bitmapRotator = new BitmapRotator(new boolean[][] {
        {false, true, false},
        {false, false, true}});
  }

  @Test
  void getWidth()
  {
    assertThat(bitmapRotator.getWidth()).isEqualTo(2);
  }

  @Test
  void getHeight()
  {
    assertThat(bitmapRotator.getHeight()).isEqualTo(3);
  }

  @Test
  void rotateRight()
  {
    assertThat(bitmapRotator.rotateRight()).isEqualTo(new boolean[][] {
        {false, true},
        {true, false},
        {false, false}});
  }
}