
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.TestUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestFifteenPuzzle {
    
    private TestUtils utils;
    private ArrayList<int[]> boards;
    
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
        this.boards = this.utils.boardList();
        
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
       FifteenPuzzle puzzle = new FifteenPuzzle(this.boards.get(9), 10);
       assertEquals(10, puzzle.moves());
       assertEquals(11, puzzle.emptyIndex());
    }
    
    @Test
    public void findEmpty() {
        assertEquals(15, this.game0.findEmpty());
        assertEquals(11, this.game2.findEmpty());
        assertEquals(1, this.game3.findEmpty());
        assertEquals(12, this.game4.findEmpty());
        assertEquals(6, this.game6.findEmpty());
    }
    
    @Test
    public void returnMinusWithIllegalBoard() {
        assertEquals(-1, new FifteenPuzzle(boards.get(11)).findEmpty());
    }
    
    @Test
    public void copiesStateCorrectly() {
        int[] s = this.boards.get(1);
        assertArrayEquals(s, this.game1.copyState());
    }
    
    @Test
    public void createChildren() {
        FifteenPuzzle child1 = this.game6.createChildren(1);
        FifteenPuzzle child2 = this.game6.createChildren(-1);
        FifteenPuzzle child3 = this.game6.createChildren(4);
        FifteenPuzzle child4 = this.game6.createChildren(-4);
        
        assertEquals(this.game6.moves() + 1, child1.moves());
        assertEquals(this.game6.moves() + 1, child2.moves());
        assertEquals(this.game6.moves() + 1, child3.moves());
        assertEquals(this.game6.moves() + 1, child4.moves());
        
        ArrayList<Puzzle> children = this.game6.generateChildren();
        
        assertTrue(child1.equals(children.get(0)));
        assertTrue(child2.equals(children.get(1)));
        assertTrue(child3.equals(children.get(2)));
        assertTrue(child4.equals(children.get(3)));
    }
    
    @Test
    public void generateChildrenEmptyInRightDownCorner() {
        int[] b1 = {
            15, 2, 1, 12,
            8, 5, 6, 11,
            4, 9, 10, 7,
            3, 14, 0, 13
        };
        int[] b2 = {
            15, 2, 1, 12,
            8, 5, 6, 11,
            4, 9, 10, 0,
            3, 14, 13, 7
        };
        
        FifteenPuzzle child1 = new FifteenPuzzle(b1);
        FifteenPuzzle child2 = new FifteenPuzzle(b2);
        
        ArrayList<Puzzle> children = this.game1.generateChildren();
        
        assertEquals(2, children.size());
        assertTrue(child1.equals(children.get(0)));
        assertTrue(child2.equals(children.get(1)));
    }
    
    @Test
    public void generateChildrenEmptyInUpCenter() {
        int[] b1 = {
            1, 2, 0, 3,
            4, 6, 5, 7,
            8, 9, 10, 11,
            12, 13, 14, 15
        };
        int[] b2 = {
            0, 1, 2, 3,
            4, 6, 5, 7,
            8, 9, 10, 11,
            12, 13, 14, 15
        };
        int[] b3 = {
            1, 6, 2, 3,
            4, 0, 5, 7,
            8, 9, 10, 11,
            12, 13, 14, 15
        };
        
        FifteenPuzzle child1 = new FifteenPuzzle(b1);
        FifteenPuzzle child2 = new FifteenPuzzle(b2);
        FifteenPuzzle child3 = new FifteenPuzzle(b3);
        
        ArrayList<Puzzle> children = this.game3.generateChildren();
        
        assertEquals(3, children.size());
        assertTrue(child1.equals(children.get(0)));
        assertTrue(child2.equals(children.get(1)));
        assertTrue(child3.equals(children.get(2)));
    }
    
    @Test
    public void generateChildrenEmptyInCenter() {
        int[] b1 = {
            1, 2, 3, 4,
            5, 6, 8, 0,
            9, 10, 11, 12,
            13, 7, 14, 15
        };
        int[] b2 = {
            1, 2, 3, 4,
            5, 0, 6, 8,
            9, 10, 11, 12,
            13, 7, 14, 15
        };
        int[] b3 = {
            1, 2, 3, 4,
            5, 6, 11, 8,
            9, 10, 0, 12,
            13, 7, 14, 15
        };
        int[] b4 = {
            1, 2, 0, 4,
            5, 6, 3, 8,
            9, 10, 11, 12,
            13, 7, 14, 15
        };
        
        FifteenPuzzle child1 = new FifteenPuzzle(b1);
        FifteenPuzzle child2 = new FifteenPuzzle(b2);
        FifteenPuzzle child3 = new FifteenPuzzle(b3);
        FifteenPuzzle child4 = new FifteenPuzzle(b4);
        
        ArrayList<Puzzle> children = this.game6.generateChildren();
        
        assertEquals(4, children.size());
        assertTrue(child1.equals(children.get(0)));
        assertTrue(child2.equals(children.get(1)));
        assertTrue(child3.equals(children.get(2)));
        assertTrue(child4.equals(children.get(3)));
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
    public void testToString() {
        String puzzle = this.game1.toString();
        String string =
                  "15\t2\t1\t12\t\n"
                + "8\t5\t6\t11\t\n"
                + "4\t9\t10\t7\t\n"
                + "3\t14\t13\t0\t\n";
        
        assertEquals(string, puzzle);
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
