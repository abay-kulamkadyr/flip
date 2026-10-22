package flip.lexer;

import flip.FlipException;

public class LexException extends FlipException {

    public LexException(String msg) {
        super(msg, -1, -1);
    }

    public LexException(String msg, int line, int column) {
        super(msg, line, column);
    }

}
