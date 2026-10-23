package flip.lexer;

import java.util.function.IntPredicate;

final class Cursor {

    private final String text;
    private int currentPosition = 0;
    private int line = 1;
    private int col = 1;

    Cursor(String text) {
        this.text = text;
    }

    int getCurrentPosition() {
        return currentPosition;
    }

    int getLine() {
        return line;
    }

    int getCol() {
        return col;
    }

    boolean atEnd() {
        return currentPosition >= text.length();
    }

    String sliceUntilCurrentPosition(int start) {
        return text.substring(start, currentPosition);
    }

    int peek() {
        return peek(0);
    }

    int peek(int offset) {
        int i = currentPosition + offset;
        return i < text.length() ? text.charAt(i) : -1;
    }

    char advance() {
        if (atEnd()) {
            throw new IllegalStateException("advance() past end of input");
        }
        char currentChar = text.charAt(currentPosition++);
        col++;
        if (currentChar == '\n' || (currentChar == '\r' && peek() != '\n')) { // detect line breaks
            line++;
            col = 1;
        }
        return currentChar;
    }

    String advanceWhile(IntPredicate predicate) {
        int start = currentPosition;
        while (!atEnd() && predicate.test(peek())) advance();
        return sliceUntilCurrentPosition(start);
    }
}
