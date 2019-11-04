
package fifteenpuzzlesolver.ui;

import fifteenpuzzlesolver.astar.AStar;
import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.astar.StateComparatorManhattan;
import fifteenpuzzlesolver.astar.StateComparatorPosition;
import java.util.Scanner;

public class TextUI {
    
    private Scanner reader;
    
    public TextUI(Scanner reader) {
        this.reader = reader;
    }
    
    public void initialize() {
        StateComparatorPosition position = new StateComparatorPosition();
        StateComparatorManhattan manhattan = new StateComparatorManhattan();
        
        int[] board = {
            15, 2, 1, 12,
            8, 5, 6, 11,
            4, 9, 10, 7,
            3, 14, 13, 0
        };
        
        Puzzle puzzle = new FifteenPuzzle(board);
        
        if (!puzzle.isSolvable()) {
            System.out.println("Puzzle is not solvable");
            return;
        }
        
        AStar astar = new AStar();
        
        long time1 = System.currentTimeMillis();
        Puzzle endState = astar.traverse(puzzle, position);
        long time2 = System.currentTimeMillis();
        
        System.out.println(endState.toString());
        System.out.println("Time taken: " + (time2 - time1) / 1000 + "seconds");
        
    }
    
}
