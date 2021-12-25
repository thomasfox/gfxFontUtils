package com.github.thomasfox.gfxfont;

import java.util.List;

public class FontParser
{
  private final String toParse;

  public FontParser(String toParse)
  {
    this.toParse = toParse;
  }

  public Font parse()
  {
    Font font = new Font();

    String fontString = getContentInCurlyBracketsAfterKeyword("const GFXfont ", toParse);
    CommaSeparatedStringParser fontStringParser = new CommaSeparatedStringParser(fontString);

    String bitmapsVariableName = getTrimmedContentAfterRoundClosingBracket(
        fontStringParser.nextArgumentString());
    String bitmapsString = getContentInCurlyBracketsAfterKeyword(bitmapsVariableName + "[]", toParse);

    String glyphsVariableName
        = getTrimmedContentAfterRoundClosingBracket(fontStringParser.nextArgumentString());
    String glyphsString = getContentInCurlyBracketsAfterKeyword(glyphsVariableName + "[]", toParse);

    List<Glyph> glyphs = new GfxGlyphParser(bitmapsString, glyphsString).parse();
    font.setGlyphList(glyphs);

    font.setFirstChar(ByteParser.parseByte(fontStringParser.nextArgumentString()));
    font.setLastChar(ByteParser.parseByte(fontStringParser.nextArgumentString()));
    font.setLineHeight(Integer.parseInt(fontStringParser.nextArgumentString().trim()));

    return font;
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

  String getTrimmedContentAfterRoundClosingBracket(String content)
  {
    int indexOfRoundClosingBracket = content.indexOf(')');
    return content.substring(indexOfRoundClosingBracket + 1).trim();
  }
}
