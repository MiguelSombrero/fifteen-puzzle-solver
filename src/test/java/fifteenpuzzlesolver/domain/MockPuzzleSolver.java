
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.TestUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for mocking PuzzleSolver. Used for testing PuzzleSolver classes benchmark method.
 * 
 * @author miika
 */
public class MockPuzzleSolver implements PuzzleSolver {
    
    private ArrayList<int[]> boards;
    
    public MockPuzzleSolver() {
        TestUtils utils = new TestUtils();
        this.boards = utils.boardList();
    }
    
    @Override
    public Puzzle solve(Puzzle puzzle) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MockPuzzleSolver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new FifteenPuzzle(boards.get(0), puzzle.getMoves());
    }
}
