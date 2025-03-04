
package fifteenpuzzlesolver;

import fifteenpuzzlesolver.domain.AStar;
import fifteenpuzzlesolver.domain.IDAStar;
import fifteenpuzzlesolver.service.PuzzleService;
import fifteenpuzzlesolver.domain.StateComparatorLinearCollision;
import fifteenpuzzlesolver.domain.StateComparatorManhattan;
import fifteenpuzzlesolver.domain.StateComparatorPosition;
import fifteenpuzzlesolver.ui.TextUI;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import java.util.Random;
import java.util.Scanner;

/**
 * Main class for starting the application.
 * @author miika
 */
public class FifteenPuzzleSolver {
    
    /**
     * Main method that starts the app.
     * @param args Possible commandline arguments given to app
     */
    public static void main(String[] args) {
        StateComparatorLinearCollision linear = new StateComparatorLinearCollision();
        StateComparatorManhattan manhattan = new StateComparatorManhattan();
        StateComparatorPosition position = new StateComparatorPosition();
        
        Random random = new Random();
        PuzzleGenerator generator = new PuzzleGenerator(random);
        Scanner reader = new Scanner(System.in);
        
        AStar astarPosition = new AStar(position);
        AStar astarManhattan = new AStar(manhattan);
        AStar astarLinear = new AStar(linear);
        
        IDAStar idaStarPosition = new IDAStar(position);
        IDAStar idaStarManhattan = new IDAStar(manhattan);
        IDAStar idaStarLinear = new IDAStar(linear);
        
        PuzzleService service = new PuzzleService(generator, astarPosition, astarManhattan, astarLinear,
                idaStarPosition, idaStarManhattan, idaStarLinear);
        
        TextUI ui = new TextUI(reader, service);
        
        ui.initialize();
        
    }
}
