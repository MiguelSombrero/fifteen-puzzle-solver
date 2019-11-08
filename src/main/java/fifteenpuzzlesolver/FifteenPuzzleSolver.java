
package fifteenpuzzlesolver;

import fifteenpuzzlesolver.service.AStar;
import fifteenpuzzlesolver.service.PuzzleService;
import fifteenpuzzlesolver.service.StateComparatorManhattan;
import fifteenpuzzlesolver.service.StateComparatorPosition;
import fifteenpuzzlesolver.ui.TextUI;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import java.util.Scanner;

/**
 * Main class for starting the application.
 * @author miika
 */
public class FifteenPuzzleSolver {
    public static void main(String[] args) {
        StateComparatorManhattan manhattan = new StateComparatorManhattan();
        StateComparatorPosition position = new StateComparatorPosition();
        PuzzleGenerator generator = new PuzzleGenerator();
        Scanner reader = new Scanner(System.in);
        AStar astar = new AStar();
        
        PuzzleService service = new PuzzleService(astar, generator, manhattan, position);
        TextUI ui = new TextUI(reader, service);
        
        ui.initialize();
    }
}
