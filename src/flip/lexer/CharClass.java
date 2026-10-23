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
        return switch (c) {
            case int ch when CharUtil.isValidLabelStart(ch) -> LABEL_START;
            case int ch when CharUtil.isDigit(ch) -> DIGIT;
            case int ch when CharUtil.isQuote(ch) -> QUOTE;
            case int ch when CharUtil.isSymbol(ch) -> SYMBOL;
            default -> ILLEGAL;
        };
    }
}
