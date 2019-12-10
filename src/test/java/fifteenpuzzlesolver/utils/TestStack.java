
package fifteenpuzzlesolver.utils;

import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.Puzzle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestStack {
    
    private Stack<Puzzle> stack;
    
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
    
    public TestStack() {
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
        this.stack = new Stack<>();
    }
    
    @Test
    public void sizeOfCreatedStackIsZero() {
        assertEquals(0, this.stack.size());
    }
    
    @Test
    public void sizeIncrementsWhenAddingElement() {
        this.stack.push(this.puzzle0);
        assertEquals(1, this.stack.size());
    }
    
    @Test
    public void sizeIncrementsWhenAddingElements() {
        this.stack.push(this.puzzle0);
        this.stack.push(this.puzzle1);
        this.stack.push(this.puzzle2);
        this.stack.push(this.puzzle3);
        this.stack.push(this.puzzle4);
        assertEquals(5, this.stack.size());
    }
    
    @Test
    public void stackExctractsPuzzlesInRightOrder() {
        this.stack.push(this.puzzle0);
        this.stack.push(this.puzzle1);
        this.stack.push(this.puzzle2);
        this.stack.push(this.puzzle3);
        this.stack.push(this.puzzle4);
        this.stack.push(this.puzzle5);
        this.stack.push(this.puzzle6);
        this.stack.push(this.puzzle7);
        this.stack.push(this.puzzle8);
        this.stack.push(this.puzzle9);
        
        assertEquals(this.puzzle9, this.stack.pop());
        assertEquals(this.puzzle8, this.stack.pop());
        assertEquals(this.puzzle7, this.stack.pop());
        assertEquals(this.puzzle6, this.stack.pop());
        assertEquals(this.puzzle5, this.stack.pop());
        assertEquals(this.puzzle4, this.stack.pop());
        assertEquals(this.puzzle3, this.stack.pop());
        assertEquals(this.puzzle2, this.stack.pop());
        assertEquals(this.puzzle1, this.stack.pop());
        assertEquals(this.puzzle0, this.stack.pop());
    }
    
    @Test
    public void stackPeeksPuzzlesInRightOrder() {
        this.stack.push(this.puzzle0);
        this.stack.push(this.puzzle1);
        this.stack.push(this.puzzle2);
        this.stack.push(this.puzzle3);
        this.stack.push(this.puzzle4);
        this.stack.push(this.puzzle5);
        this.stack.push(this.puzzle6);
        this.stack.push(this.puzzle7);
        this.stack.push(this.puzzle8);
        this.stack.push(this.puzzle9);
        
        assertEquals(this.puzzle9, this.stack.peek());
        this.stack.pop();
        assertEquals(this.puzzle8, this.stack.peek());
        this.stack.pop();
        assertEquals(this.puzzle7, this.stack.peek());
        this.stack.pop();
        assertEquals(this.puzzle6, this.stack.peek());
    }
    
    @Test
    public void stackIncrementsItsSize() {
        for (int i = 0; i < 15; i++) {
            this.stack.push(this.puzzle0);
        }
        assertEquals(15, this.stack.size());
        
        for (int i = 0; i < 15; i++) {
            this.stack.push(this.puzzle10);
        }
        assertEquals(30, this.stack.size());
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void popThrowsIndexOutOfBoundsError() {
        this.stack.pop();
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void peekThrowsIndexOutOfBoundsError() {
        this.stack.peek();
    }
    
    @Test
    public void sizeDecreasesWhenPoppingElements() {
        this.stack.push(this.puzzle6);
        this.stack.push(this.puzzle7);
        this.stack.push(this.puzzle8);
        this.stack.push(this.puzzle9);
        
        assertEquals(4, this.stack.size());
        this.stack.pop();
        assertEquals(3, this.stack.size());
        this.stack.pop();
        assertEquals(2, this.stack.size());
        this.stack.pop();
        assertEquals(1, this.stack.size());
        this.stack.pop();
        assertEquals(0, this.stack.size());
    }
    
    @Test
    public void sizeDoesntDecreaseWhenPeekingElements() {
        this.stack.push(this.puzzle6);
        this.stack.push(this.puzzle7);
        this.stack.push(this.puzzle8);
        this.stack.push(this.puzzle9);
        
        assertEquals(4, this.stack.size());
        this.stack.peek();
        assertEquals(4, this.stack.size());
        this.stack.peek();
        assertEquals(4, this.stack.size());
        this.stack.peek();
        assertEquals(4, this.stack.size());
        this.stack.peek();
        assertEquals(4, this.stack.size());
    }
    
    @Test
    public void isEmpty() {
        assertTrue(this.stack.isEmpty());
    }
    
    @Test
    public void isNotEmpty() {
        this.stack.push(this.puzzle6);
        assertTrue(!this.stack.isEmpty());
    }
    
    @Test
    public void isEmptyWhenAddedAndRemoved() {
        this.stack.push(this.puzzle6);
        this.stack.pop();
        assertTrue(this.stack.isEmpty());
    }
    
    @Test
    public void stackContainsElements() {
        this.stack.push(this.puzzle6);
        this.stack.push(this.puzzle7);
        this.stack.push(this.puzzle8);
        this.stack.push(this.puzzle9);
        
        assertTrue(this.stack.contains(this.puzzle6));
        assertTrue(this.stack.contains(this.puzzle7));
        assertTrue(this.stack.contains(this.puzzle8));
        assertTrue(this.stack.contains(this.puzzle9));
        assertTrue(!this.stack.contains(this.puzzle0));
        assertTrue(!this.stack.contains(this.puzzle1));
        assertTrue(!this.stack.contains(this.puzzle2));
        assertTrue(!this.stack.contains(this.puzzle3));
    }
    
    @Test
    public void stackDoesntContainRemovedElements() {
        this.stack.push(this.puzzle6);
        this.stack.push(this.puzzle7);
        this.stack.push(this.puzzle8);
        this.stack.push(this.puzzle9);
        
        this.stack.pop();
        assertTrue(!this.stack.contains(this.puzzle9));
        assertTrue(this.stack.contains(this.puzzle8));
        assertTrue(this.stack.contains(this.puzzle7));
        assertTrue(this.stack.contains(this.puzzle6));
        
        this.stack.pop();
        assertTrue(!this.stack.contains(this.puzzle9));
        assertTrue(!this.stack.contains(this.puzzle8));
        assertTrue(this.stack.contains(this.puzzle7));
        assertTrue(this.stack.contains(this.puzzle6));
        
        this.stack.pop();
        assertTrue(!this.stack.contains(this.puzzle9));
        assertTrue(!this.stack.contains(this.puzzle8));
        assertTrue(!this.stack.contains(this.puzzle7));
        assertTrue(this.stack.contains(this.puzzle6));
        
        this.stack.pop();
        assertTrue(!this.stack.contains(this.puzzle9));
        assertTrue(!this.stack.contains(this.puzzle8));
        assertTrue(!this.stack.contains(this.puzzle7));
        assertTrue(!this.stack.contains(this.puzzle6));
    }
    
}
