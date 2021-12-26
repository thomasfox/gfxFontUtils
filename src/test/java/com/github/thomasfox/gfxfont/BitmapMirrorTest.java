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
    bitmapMirror = new BitmapMirror(new Bitmap(new boolean[][] {
        {false, true, false},
        {false, false, true}}));
  }

  @Test
  void mirrorX()
  {
    assertThat(bitmapMirror.mirrorX()).isEqualTo(new Bitmap(new boolean[][] {
        {false, false, true},
        {false, true, false}}));
  }

  @Test
  void mirrorY()
  {
    assertThat(bitmapMirror.mirrorY()).isEqualTo(new Bitmap(new boolean[][] {
        {false, true, false},
        {true, false, false}}));
  }
}