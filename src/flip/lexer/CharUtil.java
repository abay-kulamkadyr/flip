package flip.lexer;

final class CharUtil {

    private static final String ALLOWED_SYMBOLS = "()[],:=;";

    private CharUtil() {}

    static boolean isWhitespace(int c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    static boolean isDigit(int c) {
        return c >= '0' && c <= '9';
    }

    static boolean isQuote(int c) {
        return c == '"';
    }

    static boolean isValidLabelStart(int c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    static boolean isLabelPart(int c) {
        return isValidLabelStart(c) || isDigit(c);
    }

    static boolean isSymbol(int c) {
        return ALLOWED_SYMBOLS.indexOf(c) >= 0;
    }

    static boolean isStringContent(int c) {
        return c >= 0
                && c != '"' // disallow '"' in the middle of a string
                && c != '\\' // disallow escapes sequences
                && c != '\n' // disallow multiline strings
                && c != '\r';
    }
}
