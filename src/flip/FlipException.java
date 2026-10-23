package flip;

public class FlipException extends Exception {

    private final String msg;
    private final int line;
    private final int column;

    public FlipException(String msg, int line, int column) {
        this.msg = msg;
        this.line = line;
        this.column = column;
    }

    public String getMessage() {
        return msg;
    }

    public int getLine() {
        return line;
    }

    public int getLColumn() {
        return column;
    }

    public String toString() {
        return msg + "; at line:" + line + " column:" + column;
    }
}
