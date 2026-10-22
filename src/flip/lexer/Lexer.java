package flip.lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Lexer {

    private final File progFile;

    public Lexer(File progFile) {
        this.progFile = progFile;
    }

    public List<Token> tokenize() throws LexException, IOException {

        List<Token> tokens = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(progFile));

        // TODO: Implement tokenization here

        return tokens;
    }

}
