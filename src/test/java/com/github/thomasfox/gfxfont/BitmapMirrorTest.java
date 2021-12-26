package com.github.thomasfox.gfxfont;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BitmapMirrorTest
{
  private BitmapMirror bitmapMirror;

  @BeforeEach
  void createBitmapMirror()
  {
    bitmapMirror = new BitmapMirror(new boolean[][] {
        {false, true, false},
        {false, false, true}});
  }

  @Test
  void getWidth()
  {
    assertThat(bitmapMirror.getWidth()).isEqualTo(2);
  }

  @Test
  void getHeight()
  {
    assertThat(bitmapMirror.getHeight()).isEqualTo(3);
  }

  @Test
  void mirrorX()
  {
    assertThat(bitmapMirror.mirrorX()).isEqualTo(new boolean[][] {
        {false, false, true},
        {false, true, false}});
  }

  @Test
  void mirrorY()
  {
    assertThat(bitmapMirror.mirrorY()).isEqualTo(new boolean[][] {
        {false, true, false},
        {true, false, false}});
  }
}