import flip.Flip;
import flip.FlipException;

import java.io.*;
import java.util.Arrays;

public class TestFlip {
    public static void main(String[] args) throws Exception {

        File testDir = new File("tests");
        File[] sorted = testDir.listFiles();
        Arrays.sort(sorted);
        for (File flipFile : sorted) {

            if (!flipFile.getName().endsWith(".flip")) {
                continue;
            }
            try {
                System.out.println(" *** Processing " + flipFile.getName() + " ***");
                Flip flip = new Flip(flipFile);
                flip.processAndExecute(System.out);

            } catch (FlipException ex) {
                System.out.println(ex.getClass().getName() + " thrown: " + ex);
            }
        }
    }
}
