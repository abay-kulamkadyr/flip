package flip.lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Lexer {

    private final String source;

    public Lexer(File sourceFile) throws IOException {
        this.source = readSource(sourceFile);
    }

    public List<Token> tokenize() throws LexException {
        Cursor cursor = new Cursor(source);
        List<Token> tokens = new ArrayList<>();

        skipWhitespacesAndComments(cursor);
        while (!cursor.atEnd()) {
            tokens.add(scanToken(cursor));
            skipWhitespacesAndComments(cursor);
        }
        return tokens;
    }

    private Token scanToken(Cursor cursor) throws LexException {
        int line = cursor.getLine();
        int col = cursor.getCol();
        int c = cursor.peek();

        return switch (CharClass.of(c)) {
            case LABEL_START -> new Token(cursor.advanceWhile(CharUtil::isLabelPart), line, col);
            case DIGIT -> new Token(cursor.advanceWhile(CharUtil::isDigit), line, col);
            case QUOTE -> scanStringLiteral(cursor, line, col);
            case SYMBOL -> new Token(String.valueOf(cursor.advance()), line, col);
            case ILLEGAL -> throw new LexException("Illegal character '" + (char) c + "'", line, col);
        };
    }

    private Token scanStringLiteral(Cursor cursor, int line, int col) throws LexException {
        int start = cursor.getCurrentPosition();
        cursor.advance(); // opening quote
        cursor.advanceWhile(CharUtil::isStringContent);

        if (cursor.atEnd() || !CharUtil.isQuote(cursor.peek())) {
            throw new LexException("Invalid character in string literal", cursor.getLine(), cursor.getCol());
        }
        cursor.advance();
        return new Token(cursor.sliceUntilCurrentPosition(start), line, col);
    }

    private void skipWhitespacesAndComments(Cursor cursor) {
        while (!cursor.atEnd()) {
            int c = cursor.peek();
            if (CharUtil.isWhitespace(c)) {
                cursor.advance();
            } else if (c == '/' && cursor.peek(1) == '/') {
                while (!cursor.atEnd() && cursor.peek() != '\n' && cursor.peek() != '\r') cursor.advance();
            } else {
                return;
            }
        }
    }

    private String readSource(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[8192];
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int n;
            while ((n = reader.read(buf)) != -1) {
                sb.append(buf, 0, n);
            }
        }
        return sb.toString();
    }
}
