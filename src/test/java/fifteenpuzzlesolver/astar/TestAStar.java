
package fifteenpuzzlesolver.astar;

import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.utils.TestUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */


// KESKEN KESKEN KESKEN

public class TestAStar {
    
    private StateComparatorManhattan manhattan;
    private StateComparatorPosition position;
    private AStar astar;
    
    private FifteenPuzzle puzzle0;
    private FifteenPuzzle puzzle1;
    private FifteenPuzzle puzzle2;
    private FifteenPuzzle puzzle3;
    private FifteenPuzzle puzzle4;
    private FifteenPuzzle puzzle5;
    private FifteenPuzzle puzzle6;
    
    public TestAStar() {
        this.manhattan = new StateComparatorManhattan();
        this.position = new StateComparatorPosition();
        this.astar = new AStar();
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
        Puzzle endStateManhattan2 = this.astar.traverse(this.puzzle2, manhattan);
        Puzzle endStatePosition2 = this.astar.traverse(this.puzzle2, position);
        
        Puzzle endStateManhattan4 = this.astar.traverse(this.puzzle4, manhattan);
        Puzzle endStatePosition4 = this.astar.traverse(this.puzzle4, position);
        
        assertEquals(1, endStateManhattan2.moves());
        assertEquals(1, endStatePosition2.moves());
        assertEquals(3, endStateManhattan4.moves());
        assertEquals(3, endStatePosition4.moves());
    }
    
}
