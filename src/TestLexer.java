
import flip.lexer.LexException;
import flip.lexer.Lexer;
import flip.lexer.Token;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TestLexer {

    public static void main(String[] args) throws Exception {

        File testDir = new File("tests");
        File[] sorted = testDir.listFiles();
        Arrays.sort(sorted);
        for (File flipFile : sorted) {

            if (!flipFile.getName().endsWith(".flip")) {
                continue;
            }
            try {
                System.out.println("  *** Lexing " + flipFile.getName() + " ***");

                Lexer lexer = new Lexer(flipFile);
                List<Token> tokens = lexer.tokenize();

                System.out.println("  *** Total tokens: " + tokens.size() +  " ***");
                for (Token tok : tokens) {
                    System.out.println(tok);
                }

            } catch (LexException ex) {
                System.out.println(ex.getClass().getName() + " thrown: " + ex);
            }
        }
    }
}
