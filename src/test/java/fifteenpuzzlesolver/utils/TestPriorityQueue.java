
package fifteenpuzzlesolver.utils;

import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.domain.StateComparatorLinearCollision;
import fifteenpuzzlesolver.domain.StateComparatorManhattan;
import fifteenpuzzlesolver.domain.StateComparatorPosition;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author miika
 */
public class TestPriorityQueue {
    
    private PriorityQueue<Puzzle> queuePosition;
    private PriorityQueue<Puzzle> queueManhattan;
    private PriorityQueue<Puzzle> queueLinearCollision;
    
    private FifteenPuzzle puzzle0;
    private FifteenPuzzle puzzle1;
    private FifteenPuzzle puzzle2;
    private FifteenPuzzle puzzle3;
    private FifteenPuzzle puzzle4;
    private FifteenPuzzle puzzle5;
    private FifteenPuzzle puzzle6;
    private FifteenPuzzle puzzle7;
    private FifteenPuzzle puzzle8;
    private FifteenPuzzle puzzle9;
    private FifteenPuzzle puzzle10;
    private FifteenPuzzle puzzle11;
    private FifteenPuzzle puzzle12;
    
    public TestPriorityQueue() {
        TestUtils utils = new TestUtils();
        
        this.puzzle0 = new FifteenPuzzle(utils.boardList().get(0));
        this.puzzle1 = new FifteenPuzzle(utils.boardList().get(1));
        this.puzzle2 = new FifteenPuzzle(utils.boardList().get(2));
        this.puzzle3 = new FifteenPuzzle(utils.boardList().get(3));
        this.puzzle4 = new FifteenPuzzle(utils.boardList().get(4));
        this.puzzle5 = new FifteenPuzzle(utils.boardList().get(5));
        this.puzzle6 = new FifteenPuzzle(utils.boardList().get(6));
        this.puzzle7 = new FifteenPuzzle(utils.boardList().get(7));
        this.puzzle8 = new FifteenPuzzle(utils.boardList().get(8));
        this.puzzle9 = new FifteenPuzzle(utils.boardList().get(9));
        this.puzzle10 = new FifteenPuzzle(utils.boardList().get(10));
        this.puzzle11 = new FifteenPuzzle(utils.boardList().get(11));
        this.puzzle12 = new FifteenPuzzle(utils.boardList().get(12));
    }
    
    @Before
    public void setUp() {
        this.queuePosition = new PriorityQueue<>(new StateComparatorPosition());
        this.queueManhattan = new PriorityQueue<>(new StateComparatorManhattan());
        this.queueLinearCollision = new PriorityQueue<>(new StateComparatorLinearCollision());
    }
    
    @Test
    public void sizeOfCreatedQueueIsZero() {
        assertEquals(0, this.queuePosition.size());
        assertEquals(0, this.queueManhattan.size());
        assertEquals(0, this.queueLinearCollision.size());
    }
    
    @Test
    public void sizeIncrementsWhenAddingElement() {
        this.queuePosition.add(this.puzzle0);
        this.queueManhattan.add(this.puzzle1);
        this.queueLinearCollision.add(this.puzzle2);
        assertEquals(1, this.queuePosition.size());
        assertEquals(1, this.queueManhattan.size());
        assertEquals(1, this.queueLinearCollision.size());
    }
    
    @Test
    public void sizeIncrementsWhenAddingElements() {
        this.queuePosition.add(this.puzzle0);
        this.queuePosition.add(this.puzzle2);
        this.queueManhattan.add(this.puzzle4);
        this.queueManhattan.add(this.puzzle9);
        this.queueManhattan.add(this.puzzle11);
        assertEquals(2, this.queuePosition.size());
        assertEquals(3, this.queueManhattan.size());
    }
    
    @Test
    public void queueContainsAddedElement() {
        this.queuePosition.add(this.puzzle0);
        this.queueManhattan.add(this.puzzle1);
        this.queueLinearCollision.add(this.puzzle5);
        
        assertEquals(this.puzzle0, this.queuePosition.poll());
        assertEquals(this.puzzle1, this.queueManhattan.poll());
        assertEquals(this.puzzle5, this.queueLinearCollision.poll());
    }
    
    @Test
    public void queuePositionExctractsPuzzlesInRightOrder() {
        this.queuePosition.add(this.puzzle0);
        this.queuePosition.add(this.puzzle1);
        this.queuePosition.add(this.puzzle2);
        this.queuePosition.add(this.puzzle3);
        this.queuePosition.add(this.puzzle4);
        this.queuePosition.add(this.puzzle5);
        this.queuePosition.add(this.puzzle6);
        this.queuePosition.add(this.puzzle10);
        // add rest of the puzzles if enough time :)
        
        assertEquals(this.puzzle0, this.queuePosition.poll());
        assertEquals(this.puzzle2, this.queuePosition.poll());
        assertEquals(this.puzzle4, this.queuePosition.poll());
        assertEquals(this.puzzle6, this.queuePosition.poll());
        assertEquals(this.puzzle5, this.queuePosition.poll());
        assertEquals(this.puzzle3, this.queuePosition.poll());
        assertEquals(this.puzzle1, this.queuePosition.poll());
        assertEquals(this.puzzle10, this.queuePosition.poll());
    }
    
    @Test
    public void queueLinearCollisionExctractsPuzzlesInRightOrder() {
        this.queueLinearCollision.add(this.puzzle0);
        this.queueLinearCollision.add(this.puzzle1);
        this.queueLinearCollision.add(this.puzzle2);
        this.queueLinearCollision.add(this.puzzle3);
        this.queueLinearCollision.add(this.puzzle4);
        this.queueLinearCollision.add(this.puzzle5);
        this.queueLinearCollision.add(this.puzzle6);
        this.queueLinearCollision.add(this.puzzle12);
        // add rest of the puzzles if enough time :)
        
        assertEquals(this.puzzle0, this.queueLinearCollision.poll());
        assertEquals(this.puzzle2, this.queueLinearCollision.poll());
        assertEquals(this.puzzle4, this.queueLinearCollision.poll());
        assertEquals(this.puzzle6, this.queueLinearCollision.poll());
        assertEquals(this.puzzle5, this.queueLinearCollision.poll());
        assertEquals(this.puzzle3, this.queueLinearCollision.poll());
        assertEquals(this.puzzle12, this.queueLinearCollision.poll());
        assertEquals(this.puzzle1, this.queueLinearCollision.poll());
    }
    
    @Test
    public void queueManhattanExctractsPuzzlesInRightOrder() {
        this.queueManhattan.add(this.puzzle0);
        this.queueManhattan.add(this.puzzle1);
        this.queueManhattan.add(this.puzzle2);
        this.queueManhattan.add(this.puzzle3);
        this.queueManhattan.add(this.puzzle4);
        this.queueManhattan.add(this.puzzle5);
        this.queueManhattan.add(this.puzzle6);
        // add rest of the puzzles if enough time :)
        
        assertEquals(this.puzzle0, this.queueManhattan.poll());
        assertEquals(this.puzzle2, this.queueManhattan.poll());
        assertEquals(this.puzzle4, this.queueManhattan.poll());
        assertEquals(this.puzzle6, this.queueManhattan.poll());
        assertEquals(this.puzzle5, this.queueManhattan.poll());
        assertEquals(this.puzzle3, this.queueManhattan.poll());
        assertEquals(this.puzzle1, this.queueManhattan.poll());
    }
    
    @Test
    public void queueIncrementsItsSize() {
        for (int i = 0; i < 15; i++) {
            this.queueManhattan.add(this.puzzle0);
        }
        assertEquals(15, this.queueManhattan.size());
        
        for (int i = 0; i < 15; i++) {
            this.queueManhattan.add(this.puzzle10);
        }
        assertEquals(30, this.queueManhattan.size());
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwsIndexOutOfBoundsError() {
        this.queueLinearCollision.poll();
    }
    
    @Test
    public void isEmpty() {
        assertTrue(this.queueLinearCollision.isEmpty());
        assertTrue(this.queueManhattan.isEmpty());
        assertTrue(this.queuePosition.isEmpty());
    }
    
    @Test
    public void isNotEmpty() {
        this.queuePosition.add(this.puzzle0);
        this.queueManhattan.add(this.puzzle1);
        this.queueLinearCollision.add(this.puzzle2);
        
        assertTrue(!this.queueLinearCollision.isEmpty());
        assertTrue(!this.queueManhattan.isEmpty());
        assertTrue(!this.queuePosition.isEmpty());
    }
    
    @Test
    public void isEmptyWhenAddedAndRemoved() {
        this.queuePosition.add(this.puzzle0);
        this.queueManhattan.add(this.puzzle1);
        this.queueManhattan.add(this.puzzle9);
        this.queueLinearCollision.add(this.puzzle2);
        this.queueLinearCollision.add(this.puzzle3);
        this.queueLinearCollision.add(this.puzzle4);
        
        this.queuePosition.poll();
        this.queueManhattan.poll();
        this.queueManhattan.poll();
        this.queueLinearCollision.poll();
        this.queueLinearCollision.poll();
        this.queueLinearCollision.poll();
        
        assertTrue(this.queueLinearCollision.isEmpty());
        assertTrue(this.queueManhattan.isEmpty());
        assertTrue(this.queuePosition.isEmpty());
    }
    
}
