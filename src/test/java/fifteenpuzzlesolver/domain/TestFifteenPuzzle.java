
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.TestUtils;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestFifteenPuzzle {
    
    private TestUtils utils;
    
    /**
     * Solvable or solved games
     */
    private FifteenPuzzle game0;
    private FifteenPuzzle game2;
    private FifteenPuzzle game3;
    private FifteenPuzzle game4;
    private FifteenPuzzle game5;
    private FifteenPuzzle game6;
    
    /**
     * Un-solvable games
     */
    
    private FifteenPuzzle game1;
    private FifteenPuzzle game7;
    private FifteenPuzzle game8;
    private FifteenPuzzle game9;
    
    public TestFifteenPuzzle() {
        this.utils = new TestUtils();
        List<int[]> boards = this.utils.boardList();
        
        this.game0 = new FifteenPuzzle(boards.get(0));
        this.game2 = new FifteenPuzzle(boards.get(2));
        this.game3 = new FifteenPuzzle(boards.get(3));
        this.game4 = new FifteenPuzzle(boards.get(4));
        this.game5 = new FifteenPuzzle(boards.get(5));
        this.game6 = new FifteenPuzzle(boards.get(6));
        
        this.game1 = new FifteenPuzzle(boards.get(1));
        this.game7 = new FifteenPuzzle(boards.get(7));
        this.game8 = new FifteenPuzzle(boards.get(8));
        this.game9 = new FifteenPuzzle(boards.get(9));
        
    }
    
    @Test
    public void constructorTest1() {
        assertEquals(0, this.game1.moves());
        assertEquals(15, this.game1.emptyIndex());
    }
    
    @Test
    public void constructorTest2() {
        // kesken
    }
    
    @Test
    public void isSolved() {
        assertTrue(game0.isSolved());
    }
    
    @Test
    public void isNotSolved() {
        assertTrue(!game1.isSolved());
        assertTrue(!game2.isSolved());
        assertTrue(!game3.isSolved());
        assertTrue(!game4.isSolved());
        assertTrue(!game5.isSolved());
        assertTrue(!game6.isSolved());
        assertTrue(!game7.isSolved());
        assertTrue(!game8.isSolved());
        assertTrue(!game9.isSolved());
    }
    
    @Test
    public void inversions() {
        assertEquals(0, this.game0.inversions());
        assertEquals(45, this.game1.inversions());
        assertEquals(3, this.game2.inversions());
        assertEquals(1, this.game3.inversions());
        assertEquals(0, this.game4.inversions());
        assertEquals(2, this.game5.inversions());
        assertEquals(6, this.game6.inversions());
        assertEquals(3, this.game7.inversions());
        assertEquals(5, this.game8.inversions());
        assertEquals(4, this.game9.inversions());
    }
    
    @Test
    public void isSolvable() {
        assertTrue(game0.isSolvable());
        assertTrue(game2.isSolvable());
        assertTrue(game3.isSolvable());
        assertTrue(game4.isSolvable());
        assertTrue(game5.isSolvable());
        assertTrue(game6.isSolvable());
    }
    
    @Test
    public void isNotSolvable() {
        assertTrue(!game1.isSolvable());
        assertTrue(!game7.isSolvable());
        assertTrue(!game8.isSolvable());
        assertTrue(!game9.isSolvable());
    }
    
    @Test
    public void equals() {
        int[] b = {
            15, 2, 1, 12,
            8, 5, 6, 11,
            4, 9, 10, 7,
            3, 14, 13, 0
        };
        
        FifteenPuzzle puzzle = new FifteenPuzzle(b);
        assertTrue(puzzle.equals(game1));
    }
    
    @Test
    public void notEquals() {
        int[] b = {
            15, 2, 10, 12,
            8, 5, 6, 11,
            4, 9, 1, 7,
            3, 14, 13, 0
        };
        
        FifteenPuzzle puzzle = new FifteenPuzzle(b);
        assertTrue(!puzzle.equals(game1));
    }
    
}
