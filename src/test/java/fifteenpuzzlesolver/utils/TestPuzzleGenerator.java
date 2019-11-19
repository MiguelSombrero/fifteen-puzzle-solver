
package fifteenpuzzlesolver.utils;

import fifteenpuzzlesolver.domain.Puzzle;
import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestPuzzleGenerator {
    
    private PuzzleGenerator generator;
    
    public TestPuzzleGenerator() {
        Random random = new Random();
        this.generator = new PuzzleGenerator(random);
    }
    
    @Test
    public void generateRandomPuzzleIsFifteenPuzzle() {
        Puzzle easyPuzzle = generator.generateRandomPuzzle(2);
        Puzzle hardPuzzle = generator.generateRandomPuzzle(100);
        assertEquals(16, easyPuzzle.getState().length);
        assertEquals(16, hardPuzzle.getState().length);
    }
    
    @Test
    public void generatePuzzleByMovesIsFifteenPuzzle() {
        Puzzle p1 = generator.generatePuzzleByMoves(5);
        Puzzle p2 = generator.generatePuzzleByMoves(50);
        Puzzle p3 = generator.generatePuzzleByMoves(500);
        Puzzle p4 = generator.generatePuzzleByMoves(1000);
        assertEquals(16, p1.getState().length);
        assertEquals(16, p2.getState().length);
        assertEquals(16, p3.getState().length);
        assertEquals(16, p4.getState().length);
    }
    
    @Test
    public void generateRandomPuzzleTilesSumUp() {
        Puzzle easyPuzzle = generator.generateRandomPuzzle(2);
        Puzzle hardPuzzle = generator.generateRandomPuzzle(100);
        int easyValue = Arrays.stream(easyPuzzle.getState()).sum();
        int hardValue = Arrays.stream(hardPuzzle.getState()).sum();
        assertEquals(120, easyValue);
        assertEquals(120, hardValue);
    }
    
    @Test
    public void generatePuzzleByMovesTilesSumUp() {
        Puzzle p1 = generator.generatePuzzleByMoves(5);
        Puzzle p2 = generator.generatePuzzleByMoves(50);
        Puzzle p3 = generator.generatePuzzleByMoves(500);
        Puzzle p4 = generator.generatePuzzleByMoves(1000);
        int v1 = Arrays.stream(p1.getState()).sum();
        int v2 = Arrays.stream(p2.getState()).sum();
        int v3 = Arrays.stream(p3.getState()).sum();
        int v4 = Arrays.stream(p4.getState()).sum();
        assertEquals(120, v1);
        assertEquals(120, v2);
        assertEquals(120, v3);
        assertEquals(120, v4);
    }
    
    @Test
    public void randomPuzzleIsSolvable() {
        assertTrue(generator.generateRandomPuzzle(3).isSolvable());
        assertTrue(generator.generateRandomPuzzle(100).isSolvable());
        assertTrue(generator.generateRandomPuzzle(1000).isSolvable());
    }
    
    /*
    @Test
    public void puzzleByMovesIsSolvable() {
        assertTrue(generator.generatePuzzleByMoves(3).isSolvable());
        assertTrue(generator.generatePuzzleByMoves(50).isSolvable());
        assertTrue(generator.generatePuzzleByMoves(100).isSolvable());
        assertTrue(generator.generatePuzzleByMoves(256).isSolvable());
        assertTrue(generator.generatePuzzleByMoves(1000).isSolvable());
        assertTrue(generator.generatePuzzleByMoves(999).isSolvable());
    }
    */
}
