const uint8_t SomeDummyFontBitmaps[] PROGMEM = {
    0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xAA, 0xBB,
    0xCC, 0xDD, 0xEE, 0xFF};

const GFXglyph SomeDummyFontGlyphs[] PROGMEM = {
    {0, 0, 0, 11, 0, 1},      // 0x7B '{'
    {0, 2, 11, 11, 4, -10}};  // 0x21 '!'

const GFXfont SomeDummyFont PROGMEM = {(uint8_t *)SomeDummyFontBitmaps,
                                       (GFXglyph *)SomeDummyFontGlyphs, 0x20,
                                       0x21, 18};
