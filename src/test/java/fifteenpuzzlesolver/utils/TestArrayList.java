
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
public class TestArrayList {
    
    private ArrayList<Puzzle> list;
    private ArrayList<int[]> boards;
    
    public TestArrayList() {
        TestUtils utils = new TestUtils();
        this.boards = utils.boardList();
    }
    
    @Before
    public void setUp() {
        this.list = new ArrayList<>();
    }
    
    @Test
    public void sizeOfCreatedArrayIsZero() {
        assertEquals(0, list.size());
    }
    
    @Test
    public void sizeIncrementsWhenAddingElement() {
        this.list.add(new FifteenPuzzle(this.boards.get(1)));
        assertEquals(1, list.size());
    }
    
    @Test
    public void sizeIncrementsWhenAddingElements() {
        this.list.add(new FifteenPuzzle(this.boards.get(1)));
        this.list.add(new FifteenPuzzle(this.boards.get(2)));
        this.list.add(new FifteenPuzzle(this.boards.get(3)));
        assertEquals(3, list.size());
    }
    
    @Test
    public void listContainsAddedElements() {
        Puzzle puzzle1 = new FifteenPuzzle(this.boards.get(1));
        Puzzle puzzle2 = new FifteenPuzzle(this.boards.get(2));
        Puzzle puzzle3 = new FifteenPuzzle(this.boards.get(3));
        
        this.list.add(puzzle1);
        this.list.add(puzzle2);
        this.list.add(puzzle3);
        
        assertEquals(puzzle1, this.list.get(0));
        assertEquals(puzzle2, this.list.get(1));
        assertEquals(puzzle3, this.list.get(2));
    }
    
    @Test
    public void listIncrementsItsSize() {
        for (int i = 0; i < 15; i++) {
            this.list.add(new FifteenPuzzle(this.boards.get(3)));
        }
        assertEquals(15, this.list.size());
        
        for (int i = 0; i < 15; i++) {
            this.list.add(new FifteenPuzzle(this.boards.get(3)));
        }
        assertEquals(30, this.list.size());
    }
    
    @Test
    public void canAddOtherElementsThanPuzzles() {
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        
        integers.add(1);
        integers.add(2);
        integers.add(1234);
        
        strings.add("miika");
        strings.add("ackdlknlk KJNSDLKN");
        strings.add("TESTING IS FUN");
        strings.add("a");
        
        assertEquals((Object) 1, integers.get(0));
        assertEquals((Object) 2, integers.get(1));
        assertEquals((Object) 1234, integers.get(2));
        
        assertEquals("miika", strings.get(0));
        assertEquals("ackdlknlk KJNSDLKN", strings.get(1));
        assertEquals("TESTING IS FUN", strings.get(2));
        assertEquals("a", strings.get(3));
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwsIndexOutOfBoundsErrorLower() {
        new ArrayList<>().get(-1);
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwsIndexOutOfBoundsErrorUpper() {
        new ArrayList<>().get(1);
    }   
}
