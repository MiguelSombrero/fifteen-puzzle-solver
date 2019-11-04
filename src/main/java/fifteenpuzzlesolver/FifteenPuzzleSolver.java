
package fifteenpuzzlesolver;

import fifteenpuzzlesolver.ui.TextUI;
import java.util.Scanner;

public class FifteenPuzzleSolver {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        TextUI ui = new TextUI(reader);
        
        ui.initialize();
    }
}
