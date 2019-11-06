
package fifteenpuzzlesolver.utils;

import fifteenpuzzlesolver.domain.Puzzle;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestPuzzleGenerator {
    
    private PuzzleGenerator generator;
    
    public TestPuzzleGenerator() {
        this.generator = new PuzzleGenerator();
    }
    
    @Test
    public void generatePuzzleIsFifteenPuzzle() {
        Puzzle easyPuzzle = generator.generateEasyPuzzle();
        Puzzle hardPuzzle = generator.generateHardPuzzle();
        assertEquals(16, easyPuzzle.state().length);
        assertEquals(16, hardPuzzle.state().length);
    }
    
    @Test
    public void generatePuzzleTilesSumUp() {
        Puzzle easyPuzzle = generator.generatePuzzle(2);
        Puzzle hardPuzzle = generator.generatePuzzle(100);
        int easyValue = Arrays.stream(easyPuzzle.state()).sum();
        int hardValue = Arrays.stream(hardPuzzle.state()).sum();
        assertEquals(120, easyValue);
        assertEquals(120, hardValue);
    }
    
    @Test
    public void puzzleIsSolvable() {
        assertTrue(generator.generateEasyPuzzle().isSolvable());
        assertTrue(generator.generateHardPuzzle().isSolvable());
    }
}
