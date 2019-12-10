
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.PuzzleGenerator;
import fifteenpuzzlesolver.utils.TestUtils;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestStateComparatorLinearCollision {
    
    private StateComparatorLinearCollision comparator;
    private TestUtils utils;
    private PuzzleGenerator generator;
    
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
        this.generator = new PuzzleGenerator(new Random());
         
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
    public void isInSameRow() {
        assertTrue(comparator.isInSameRow(0, 3));
        assertTrue(comparator.isInSameRow(7, 4));
        assertTrue(comparator.isInSameRow(9, 10));
        assertTrue(comparator.isInSameRow(12, 13));
    }
    
    @Test
    public void isNotInSameRow() {
        assertTrue(!comparator.isInSameRow(0, 4));
        assertTrue(!comparator.isInSameRow(3, 4));
        assertTrue(!comparator.isInSameRow(0, 12));
        assertTrue(!comparator.isInSameRow(5, 10));
    }
    
    @Test
    public void isInSameColumn() {
        assertTrue(comparator.isInSameColumn(0, 4));
        assertTrue(comparator.isInSameColumn(5, 9));
        assertTrue(comparator.isInSameColumn(3, 15));
        assertTrue(comparator.isInSameColumn(1, 13));
    }
    
    @Test
    public void isNotInSameColumn() {
        assertTrue(!comparator.isInSameColumn(0, 5));
        assertTrue(!comparator.isInSameColumn(2, 3));
        assertTrue(!comparator.isInSameColumn(8, 11));
        assertTrue(!comparator.isInSameColumn(0, 14));
    }
    
    @Test
    public void noCollisionReturnsZero() {
        assertEquals(0, comparator.countCollision(this.utils.boardList().get(2), 0, 3));
        assertEquals(0, comparator.countCollision(this.utils.boardList().get(2), 1, 2));
        assertEquals(0, comparator.countCollision(this.utils.boardList().get(2), 0, 8));
        assertEquals(0, comparator.countCollision(this.utils.boardList().get(2), 8, 10));
        assertEquals(0, comparator.countCollision(this.utils.boardList().get(2), 5, 14));
        assertEquals(0, comparator.countCollision(this.utils.boardList().get(3), 0, 2));
        assertEquals(0, comparator.countCollision(this.utils.boardList().get(3), 3, 4));
        assertEquals(0, comparator.countCollision(this.utils.boardList().get(8), 12, 13));
        assertEquals(0, comparator.countCollision(this.utils.boardList().get(1), 5, 6));
    }
    
    @Test
    public void rowCollision() {
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(5), 1, 2));
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(5), 1, 3));
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(1), 1, 2));
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(1), 4, 5));
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(1), 4, 6));
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(1), 13, 14));
    }
    
    @Test
    public void columnCollision() {
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(12), 0, 4));
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(12), 0, 8));
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(12), 4, 8));
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(12), 2, 14));
        assertEquals(2, comparator.countCollision(this.utils.boardList().get(12), 5, 13));
    }
    
    @Test
    public void countHeuristicsCorrectly() {
        assertEquals(0, comparator.heuristic(puzzle0));
        assertEquals(40, comparator.heuristic(puzzle1));
        assertEquals(1, comparator.heuristic(puzzle2));
        assertEquals(25, comparator.heuristic(puzzle3));
        assertEquals(3, comparator.heuristic(puzzle4));
        assertEquals(11, comparator.heuristic(puzzle5));
        assertEquals(5, comparator.heuristic(puzzle6));
        assertEquals(36, comparator.heuristic(new FifteenPuzzle(utils.boardList().get(12))));
    }
    
    @Test
    public void heuristicDoesNotOverestimateCost() {
        for (int i = 0; i < 100000; i++) {
            Puzzle p = generator.generateRandomPuzzle(100);
            assertTrue(comparator.heuristic(p) <= 80);
        }
    }
    
    @Test
    public void heuristicDoesNotOverestimateCost80Moves() {
        Puzzle p = generator.generate80movesPuzzle();
        assertTrue(comparator.heuristic(p) <= 80);
    }
    
    @Test
    public void compare() {
        assertTrue(comparator.compare(puzzle0, puzzle2) < 0);
        assertTrue(comparator.compare(puzzle3, puzzle3) == 0);
        assertTrue(comparator.compare(puzzle3, puzzle1) < 0);
        assertTrue(comparator.compare(puzzle5, puzzle6) > 0);
        
        assertTrue(comparator.compare(puzzle2, new FifteenPuzzle(utils.boardList().get(0), 1)) == 0);
        assertTrue(comparator.compare(puzzle2, new FifteenPuzzle(utils.boardList().get(0), 2)) < 0);
        assertTrue(comparator.compare(puzzle1, new FifteenPuzzle(utils.boardList().get(1), 1)) < 0);
        assertTrue(comparator.compare(puzzle1, new FifteenPuzzle(utils.boardList().get(3), 16)) < 0);
        assertTrue(comparator.compare(new FifteenPuzzle(utils.boardList().get(4), 3), puzzle6) > 0);
    }
}
