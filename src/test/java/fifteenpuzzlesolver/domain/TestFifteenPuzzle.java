
package fifteenpuzzlesolver.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestFifteenPuzzle {
    
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
        // solvable boards
        int[] b0 = {
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 0
        };
        int[] b2 = {
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 0,
            13, 14, 15, 12
        };
        int[] b3 = {
            1, 0, 2, 3,
            4, 6, 5, 7,
            8, 9, 10, 11,
            12, 13, 14, 15
        };
        int[] b4 = {
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            0, 13, 14, 15
        };
        int[] b5 = {
            1, 4, 2, 3,
            5, 6, 7, 8,
            9, 10, 11, 12,
            0, 13, 14, 15
        };
        int[] b6 = {
            1, 2, 3, 4,
            5, 6, 0, 8,
            9, 10, 11, 12,
            13, 7, 14, 15
        };
        
        // un-solvable boards
        
        int[] b1 = {
            15, 2, 1, 12,
            8, 5, 6, 11,
            4, 9, 10, 7,
            3, 14, 13, 0
        };
        int[] b7 = {
            1, 4, 2, 3,
            5, 6, 7, 8,
            9, 10, 11, 12,
            0, 14, 13, 15
        };
        int[] b8 = {
            1, 2, 3, 4,
            5, 6, 0, 8,
            9, 10, 11, 12,
            7, 13, 14, 15
        };
        int[] b9 = {
            1, 3, 2, 4,
            5, 6, 7, 8,
            9, 10, 11, 0,
            13, 14, 15, 12
        };
        
        this.game0 = new FifteenPuzzle(b0);
        this.game2 = new FifteenPuzzle(b2);
        this.game3 = new FifteenPuzzle(b3);
        this.game4 = new FifteenPuzzle(b4);
        this.game5 = new FifteenPuzzle(b5);
        this.game6 = new FifteenPuzzle(b6);
        
        this.game1 = new FifteenPuzzle(b1);
        this.game7 = new FifteenPuzzle(b7);
        this.game8 = new FifteenPuzzle(b8);
        this.game9 = new FifteenPuzzle(b9);
        
    }
    
    @Before
    public void setUp() {
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
