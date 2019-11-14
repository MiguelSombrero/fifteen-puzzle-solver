
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
public class TestHashSet {
    
    private HashSet<Puzzle> set;
    private ArrayList<int[]> boards;
    private PuzzleGenerator generator;
    
    public TestHashSet() {
        TestUtils utils = new TestUtils();
        this.generator = new PuzzleGenerator();
        this.boards = utils.boardList();
    }
    
    @Before
    public void setUp() {
        this.set = new HashSet<>();
    }
    
    @Test
    public void sizeOfCreatedSetIsZero() {
        assertEquals(0, set.size());
    }
    
    @Test
    public void sizeIncrementsWhenAddingElement() {
        this.set.add(new FifteenPuzzle(this.boards.get(1)));
        assertEquals(1, set.size());
    }
    
    @Test
    public void sizeIncrementsWhenAddingElements() {
        this.set.add(new FifteenPuzzle(this.boards.get(1)));
        this.set.add(new FifteenPuzzle(this.boards.get(2)));
        this.set.add(new FifteenPuzzle(this.boards.get(3)));
        assertEquals(3, set.size());
    }
    
    @Test
    public void sizeDoesNotIncrementWhenAddingSameElement() {
        this.set.add(new FifteenPuzzle(this.boards.get(1)));
        this.set.add(new FifteenPuzzle(this.boards.get(2)));
        this.set.add(new FifteenPuzzle(this.boards.get(3)));
        this.set.add(new FifteenPuzzle(this.boards.get(2)));
        this.set.add(new FifteenPuzzle(this.boards.get(3)));
        assertEquals(3, set.size());
    }
    
    @Test
    public void setContainsAddedElements() {
        Puzzle puzzle1 = new FifteenPuzzle(this.boards.get(1));
        Puzzle puzzle2 = new FifteenPuzzle(this.boards.get(2));
        Puzzle puzzle3 = new FifteenPuzzle(this.boards.get(3));
        Puzzle puzzle4 = new FifteenPuzzle(this.boards.get(4));
        Puzzle puzzle5 = new FifteenPuzzle(this.boards.get(5));
        Puzzle puzzle6 = new FifteenPuzzle(this.boards.get(6));
        
        this.set.add(puzzle1);
        this.set.add(puzzle2);
        this.set.add(puzzle3);
        
        assertTrue(this.set.contains(puzzle1));
        assertTrue(this.set.contains(puzzle2));
        assertTrue(this.set.contains(puzzle3));
        assertTrue(!this.set.contains(puzzle4));
        assertTrue(!this.set.contains(puzzle5));
        assertTrue(!this.set.contains(puzzle6));
    }
    
    @Test
    public void setIncrementsItsSize() {
        for (int i = 0; i < 30; i++) {
            this.set.add(this.generator.generateRandomPuzzle(10));
        }
        assertEquals(30, this.set.size());
        
        for (int i = 0; i < 30; i++) {
            this.set.add(this.generator.generateRandomPuzzle(10));
        }
        assertEquals(60, this.set.size());
    }
    
    @Test
    public void canAddOtherElementsThanPuzzles() {
        HashSet<Integer> integers = new HashSet<>();
        HashSet<String> strings = new HashSet<>();
        
        integers.add(1);
        integers.add(2);
        integers.add(1234);
        
        strings.add("miika");
        strings.add("ackdlknlk KJNSDLKN");
        strings.add("TESTING IS FUN");
        strings.add("a");
        
        assertTrue(integers.contains(1));
        assertTrue(integers.contains(2));
        assertTrue(integers.contains(1234));
        assertTrue(!integers.contains(12));
        
        assertTrue(strings.contains("miika"));
        assertTrue(strings.contains("ackdlknlk KJNSDLKN"));
        assertTrue(strings.contains("TESTING IS FUN"));
        assertTrue(strings.contains("a"));
        assertTrue(!strings.contains("sd"));
    }
}
