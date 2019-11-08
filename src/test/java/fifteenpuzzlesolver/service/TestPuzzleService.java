
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import fifteenpuzzlesolver.utils.TestUtils;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestPuzzleService {
    
    private PuzzleService service;
    private List<int[]> boards;
    
    public TestPuzzleService() {
        StateComparatorManhattan manhattan = new StateComparatorManhattan();
        StateComparatorPosition position = new StateComparatorPosition();
        PuzzleGenerator generator = new PuzzleGenerator();
        TestUtils utils = new TestUtils();
        AStar astar = new AStar();
        
        this.boards = utils.boardList();
        this.service = new PuzzleService(astar, generator, manhattan, position);
    }
    
    @Test
    public void solveManhattanReturnsNullIfUnsolvable() {
        assertEquals(null, this.service.solveWithManhattan(new FifteenPuzzle(this.boards.get(1))));
    }
    
    @Test
    public void solvePositionReturnsNullIfUnsolvable() {
        assertEquals(null, this.service.solveWithPosition(new FifteenPuzzle(this.boards.get(1))));
    }
    
    @Test
    public void solveManhattanReturnsSolvedPuzzleIfSolvable() {
        assertArrayEquals(this.boards.get(0), this.service.solveWithManhattan(new FifteenPuzzle(this.boards.get(2))).state());
    }
    
    @Test
    public void solvePositionReturnsSolvedPuzzleIfSolvable() {
        assertArrayEquals(this.boards.get(0), this.service.solveWithPosition(new FifteenPuzzle(this.boards.get(2))).state());
    }
    
    @Test
    public void generatePuzzleIsFifteenPuzzle() {
        Puzzle easyPuzzle = this.service.generateEasyPuzzle();
        Puzzle hardPuzzle = this.service.generateHardPuzzle();
        assertEquals(16, easyPuzzle.state().length);
        assertEquals(16, hardPuzzle.state().length);
    }
    
    @Test
    public void generatePuzzleTilesSumUp() {
        Puzzle easyPuzzle = this.service.generateEasyPuzzle();
        Puzzle hardPuzzle = this.service.generateHardPuzzle();
        int easyValue = Arrays.stream(easyPuzzle.state()).sum();
        int hardValue = Arrays.stream(hardPuzzle.state()).sum();
        assertEquals(120, easyValue);
        assertEquals(120, hardValue);
    }
    
    @Test
    public void puzzleIsSolvable() {
        assertTrue(this.service.generateEasyPuzzle().isSolvable());
        assertTrue(this.service.generateHardPuzzle().isSolvable());
    }
    
    
}
