
package fifteenpuzzlesolver;

import fifteenpuzzlesolver.astar.AStar;
import fifteenpuzzlesolver.ui.TextUI;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import java.util.Scanner;

/**
 * Main class for starting the application.
 * @author miika
 */
public class FifteenPuzzleSolver {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        AStar astar = new AStar();
        PuzzleGenerator generator = new PuzzleGenerator();
        
        TextUI ui = new TextUI(reader, astar, generator);
        
        ui.initialize();
    }
}
