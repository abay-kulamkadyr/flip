package flip;

import flip.lexer.Lexer;
import flip.lexer.Token;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Flip {

    private final File progFile;

    public Flip(File progFile) {
        this.progFile = progFile;
    }

    public void processAndExecute(PrintStream output) throws FlipException, IOException {

        // Step 1.  Tokenize
        Lexer lexer = new Lexer(progFile);
        List<Token> tokens = lexer.tokenize();

        // Step 2.  Parse
        // Parser parser = new Parser(tokens);
        // FlipProgram program = parser.parse();

        // Step 3.  Semantic Analysis
        // SemanticAnalyzer analyzer = new SemanticAnalyzer(program);
        // analyzer.analyze();

        // Step 4.  Executor
        // Executor executor = new Executor(program);
        // executor.runProgram(output);

        //  THE END!
    }
}
