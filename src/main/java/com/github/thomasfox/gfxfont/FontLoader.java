package com.github.thomasfox.gfxfont;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FontLoader
{
  private String glyphsString;

  private String fontString;

  private String bitmapsString;

  List<Glyph> glyphs;

  public void load(String filename)
  {
    try (FileInputStream fis = new FileInputStream(filename))
    {
      String fileContent = new String(fis.readAllBytes(), StandardCharsets.ISO_8859_1);

      fontString = getContentInCurlyBracketsAfterKeyword("const GFXfont ", fileContent);

      String bitmapsVariableName = getTrimmedContentBetweenRoundClosingBracketAndComma(fontString);
      bitmapsString = getContentInCurlyBracketsAfterKeyword(bitmapsVariableName + "[]", fileContent);

      String glyphsVariableName
          = getTrimmedContentBetweenRoundClosingBracketAndComma(getAfterNthComma(1, fontString));
      glyphsString = getContentInCurlyBracketsAfterKeyword(glyphsVariableName + "[]", fileContent);

      glyphs = new GfxGlyphParser(bitmapsString, glyphsString).parse();
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  String getContentInCurlyBracketsAfterKeyword(String keyword, String content)
  {
    int indexOfKeyword = content.indexOf(keyword);
    int indexOfOpeningBracket = content.indexOf('{', indexOfKeyword);

    // check for and ignore nested {...} combinations
    int lookFrom = indexOfOpeningBracket + 1;
    int indexOfNextClosingBracket;
    while (true)
    {
      int indexOfNextOpeningBracket = content.indexOf('{', lookFrom);
      indexOfNextClosingBracket = content.indexOf('}', lookFrom);
      if (indexOfNextOpeningBracket != -1 && indexOfNextClosingBracket > indexOfNextOpeningBracket)
      {
        lookFrom = indexOfNextClosingBracket + 1;
      }
      else
      {
        break;
      }
    }
    return content.substring(indexOfOpeningBracket + 1, indexOfNextClosingBracket);
  }

  String getTrimmedContentBetweenRoundClosingBracketAndComma(String content)
  {
    int indexOfRoundClosingBracket = content.indexOf(')');
    int indexOfComma = content.indexOf(',', indexOfRoundClosingBracket);
    return content.substring(indexOfRoundClosingBracket + 1, indexOfComma).trim();
  }

  String getAfterNthComma(int n, String content)
  {
    int indexOfComma = -1;
    for (int i = 0; i < n; i++)
    {
      indexOfComma = content.indexOf(',', indexOfComma + 1);
      if (indexOfComma == -1)
      {
        throw new RuntimeException("No " + n + " commas in " + content);
      }
    }
    return content.substring(indexOfComma + 1);

  }

  String getFontString() {
    return fontString;
  }

  String getBitmapsString() {
    return bitmapsString;
  }

  String getGlyphsString() {
    return glyphsString;
  }

  public List<Glyph> getGlyphs()
  {
    return glyphs;
  }
}
