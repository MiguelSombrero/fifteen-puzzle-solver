
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
    public void puzzleWith80MovesIsZero() {
        Puzzle p1 = generator.generate80movesPuzzle();
        assertEquals(0, p1.getMoves());
    }
    
    @Test
    public void randomPuzzleMovesIsZero() {
        Puzzle p1 = generator.generateRandomPuzzle(10);
        Puzzle p2 = generator.generateRandomPuzzle(100);
        assertEquals(0, p1.getMoves());
        assertEquals(0, p2.getMoves());
    }
    
    @Test
    public void puzzleByMovesMovesIsZero() {
        Puzzle p1 = generator.generatePuzzleByMoves(10);
        Puzzle p2 = generator.generatePuzzleByMoves(100);
        assertEquals(0, p1.getMoves());
        assertEquals(0, p2.getMoves());
    }
    
    @Test
    public void generatePuzzleWith80MovesIsFifteenPuzzle() {
        Puzzle p1 = generator.generate80movesPuzzle();
        assertEquals(16, p1.getState().length);
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
    public void generatePuzzleWith80MovesTilesSumUp() {
        Puzzle p1 = generator.generate80movesPuzzle();
        int s1 = Arrays.stream(p1.getState()).sum();
        assertEquals(120, s1);
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
        int v1 = Arrays.stream(p1.getState()).sum();
        int v2 = Arrays.stream(p2.getState()).sum();
        int v3 = Arrays.stream(p3.getState()).sum();
        assertEquals(120, v1);
        assertEquals(120, v2);
        assertEquals(120, v3);
    }
    
    @Test
    public void puzzleWith80MovesIsSolvable() {
        assertTrue(generator.generate80movesPuzzle().isSolvable());
    }
    
    @Test
    public void randomPuzzleIsSolvable() {
        assertTrue(generator.generateRandomPuzzle(3).isSolvable());
        assertTrue(generator.generateRandomPuzzle(100).isSolvable());
        assertTrue(generator.generateRandomPuzzle(1000).isSolvable());
    }
    
    @Test
    public void puzzleByMovesIsSolvable() {
        assertTrue(generator.generatePuzzleByMoves(3).isSolvable());
        assertTrue(generator.generatePuzzleByMoves(50).isSolvable());
        assertTrue(generator.generatePuzzleByMoves(100).isSolvable());
        assertTrue(generator.generatePuzzleByMoves(256).isSolvable());
    }
}
