package flip.lexer;

/**
 * Classifies a character's type
 */
enum CharClass {
    LABEL_START,
    DIGIT,
    QUOTE,
    SYMBOL,
    ILLEGAL;

    static CharClass of(int c) {
        if (CharUtil.isValidLabelStart(c)) return LABEL_START;
        if (CharUtil.isDigit(c)) return DIGIT;
        if (CharUtil.isQuote(c)) return QUOTE;
        if (CharUtil.isSymbol(c)) return SYMBOL;
        return ILLEGAL;
    }
}
