
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.utils.TestUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestStateComparatorLinearCollision {
    
    
    // kesken kesken kesken
    
    private StateComparatorLinearCollision comparator;
    private TestUtils utils;
    private FifteenPuzzle puzzle0;
    private FifteenPuzzle puzzle1;
    private FifteenPuzzle puzzle2;
    private FifteenPuzzle puzzle3;
    private FifteenPuzzle puzzle4;
    private FifteenPuzzle puzzle5;
    private FifteenPuzzle puzzle6;
        
    public TestStateComparatorLinearCollision() {
        this.comparator = new StateComparatorLinearCollision();
        this.utils = new TestUtils();
        
        this.puzzle0 = new FifteenPuzzle(utils.boardList().get(0));
        this.puzzle1 = new FifteenPuzzle(utils.boardList().get(1));
        this.puzzle2 = new FifteenPuzzle(utils.boardList().get(2));
        this.puzzle3 = new FifteenPuzzle(utils.boardList().get(3));
        this.puzzle4 = new FifteenPuzzle(utils.boardList().get(4));
        this.puzzle5 = new FifteenPuzzle(utils.boardList().get(5));
        this.puzzle6 = new FifteenPuzzle(utils.boardList().get(6));
    }
    
    @Test
    public void countManhattanDistance() {
        assertEquals(2, comparator.countManhattanDistance(9, 12));
        assertEquals(3, comparator.countManhattanDistance(0, 12));
        assertEquals(6, comparator.countManhattanDistance(3, 12));
        assertEquals(2, comparator.countManhattanDistance(9, 12));
        assertEquals(0, comparator.countManhattanDistance(6, 6));
        assertEquals(4, comparator.countManhattanDistance(3, 9));
        assertEquals(4, comparator.countManhattanDistance(4, 11));
        assertEquals(3, comparator.countManhattanDistance(8, 11));
        assertEquals(1, comparator.countManhattanDistance(0, 1));
        assertEquals(5, comparator.countManhattanDistance(1, 15));
    }
    
    @Test
    public void countLinearCollision() {
        // kesken
    }
    
    @Test
    public void countHeuristicsCorrectly() {
        // kesken
    }
    
    @Test
    public void compare() {
        // kesken
    }
}
