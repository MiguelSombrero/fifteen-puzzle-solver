
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.TestUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestStateComparatorPosition {
    
    private StateComparatorPosition comparator;
    private TestUtils utils;
    private FifteenPuzzle puzzle0;
    private FifteenPuzzle puzzle1;
    private FifteenPuzzle puzzle2;
    private FifteenPuzzle puzzle3;
    private FifteenPuzzle puzzle4;
    private FifteenPuzzle puzzle5;
    private FifteenPuzzle puzzle6;
    private FifteenPuzzle puzzle10;
    
    public TestStateComparatorPosition() {
        this.comparator = new StateComparatorPosition();
        this.utils = new TestUtils();
        
        this.puzzle0 = new FifteenPuzzle(utils.boardList().get(0));
        this.puzzle1 = new FifteenPuzzle(utils.boardList().get(1));
        this.puzzle2 = new FifteenPuzzle(utils.boardList().get(2));
        this.puzzle3 = new FifteenPuzzle(utils.boardList().get(3));
        this.puzzle4 = new FifteenPuzzle(utils.boardList().get(4));
        this.puzzle5 = new FifteenPuzzle(utils.boardList().get(5));
        this.puzzle6 = new FifteenPuzzle(utils.boardList().get(6));
        
        this.puzzle10 = new FifteenPuzzle(utils.boardList().get(10));
    }
    
    @Test
    public void countsHeuristicCorrectly() {
        assertEquals(0, comparator.heuristic(puzzle0));
        assertEquals(13, comparator.heuristic(puzzle1));
        assertEquals(1, comparator.heuristic(puzzle2));
        assertEquals(13, comparator.heuristic(puzzle3));
        assertEquals(3, comparator.heuristic(puzzle4));
        assertEquals(6, comparator.heuristic(puzzle5));
        assertEquals(3, comparator.heuristic(puzzle6));
        assertEquals(14, comparator.heuristic(puzzle10));
    }
    
    @Test
    public void compare() {
        assertTrue(comparator.compare(puzzle0, puzzle2) < 0);
        assertTrue(comparator.compare(puzzle3, puzzle3) == 0);
        assertTrue(comparator.compare(puzzle5, puzzle1) < 0);
        assertTrue(comparator.compare(puzzle5, puzzle4) > 0);
        
        assertTrue(comparator.compare(puzzle2, new FifteenPuzzle(utils.boardList().get(0), 1)) == 0);
        assertTrue(comparator.compare(puzzle2, new FifteenPuzzle(utils.boardList().get(0), 2)) < 0);
        assertTrue(comparator.compare(puzzle1, new FifteenPuzzle(utils.boardList().get(1), 1)) < 0);
        assertTrue(comparator.compare(puzzle1, new FifteenPuzzle(utils.boardList().get(5), 10)) < 0);
        
    }
    
}
