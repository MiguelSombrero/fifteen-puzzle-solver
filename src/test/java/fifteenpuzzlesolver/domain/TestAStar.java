
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.TestUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */


// KESKEN KESKEN KESKEN

public class TestAStar {
    
    private AStar astarPosition;
    private AStar astarManhattan;
    private AStar astarLinear;
    
    private FifteenPuzzle puzzle0;
    private FifteenPuzzle puzzle1;
    private FifteenPuzzle puzzle2;
    private FifteenPuzzle puzzle3;
    private FifteenPuzzle puzzle4;
    private FifteenPuzzle puzzle5;
    private FifteenPuzzle puzzle6;
    
    public TestAStar() {
        this.astarPosition = new AStar(new StateComparatorPosition());
        this.astarManhattan = new AStar(new StateComparatorManhattan());
        this.astarLinear = new AStar(new StateComparatorLinearCollision());
        
        TestUtils utils = new TestUtils();
        
        this.puzzle0 = new FifteenPuzzle(utils.boardList().get(0));
        this.puzzle1 = new FifteenPuzzle(utils.boardList().get(1));
        this.puzzle2 = new FifteenPuzzle(utils.boardList().get(2));
        this.puzzle3 = new FifteenPuzzle(utils.boardList().get(3));
        this.puzzle4 = new FifteenPuzzle(utils.boardList().get(4));
        this.puzzle5 = new FifteenPuzzle(utils.boardList().get(5));
        this.puzzle6 = new FifteenPuzzle(utils.boardList().get(6));
    }
    
    @Test
    public void aStarFindsOptimalPath() {
        assertEquals(1, this.astarManhattan.solve(this.puzzle2).getMoves());
        assertEquals(1, this.astarPosition.solve(this.puzzle2).getMoves());
        assertEquals(3, this.astarManhattan.solve(this.puzzle4).getMoves());
        assertEquals(3, this.astarPosition.solve(this.puzzle4).getMoves());
    }
    
}
