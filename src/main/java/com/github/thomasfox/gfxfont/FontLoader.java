package com.github.thomasfox.gfxfont;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.github.thomasfox.gfxfont.parse.FontParser;

public class FontLoader
{
  public Font load(String filename)
  {
    try (FileInputStream fis = new FileInputStream(filename))
    {
      String fileContent = new String(fis.readAllBytes(), StandardCharsets.ISO_8859_1);

      FontParser fontParser = new FontParser(fileContent);
      return fontParser.parse();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}
