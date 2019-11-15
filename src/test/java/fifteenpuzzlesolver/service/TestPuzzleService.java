
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import fifteenpuzzlesolver.utils.TestUtils;
import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miika
 */
public class TestPuzzleService {
    
    
    
    // Still missing test for generatePuzzleByMoves and more!!
    
    
    
    private PuzzleService service;
    private ArrayList<int[]> boards;
    
    public TestPuzzleService() {
        StateComparatorLinearCollision linear = new StateComparatorLinearCollision();
        StateComparatorManhattan manhattan = new StateComparatorManhattan();
        StateComparatorPosition position = new StateComparatorPosition();
        Random random = new Random();
        PuzzleGenerator generator = new PuzzleGenerator(random);
        TestUtils utils = new TestUtils();
        AStar astar = new AStar();
        
        this.boards = utils.boardList();
        this.service = new PuzzleService(astar, generator, manhattan, position, linear);
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
        assertArrayEquals(this.boards.get(0), this.service.solveWithManhattan(new FifteenPuzzle(this.boards.get(2))).getState());
    }
    
    @Test
    public void solvePositionReturnsSolvedPuzzleIfSolvable() {
        assertArrayEquals(this.boards.get(0), this.service.solveWithPosition(new FifteenPuzzle(this.boards.get(2))).getState());
    }
    
    @Test
    public void generatePuzzleIsFifteenPuzzle() {
        Puzzle easyPuzzle = this.service.generateRandomPuzzle(2);
        Puzzle hardPuzzle = this.service.generateRandomPuzzle(100);
        assertEquals(16, easyPuzzle.getState().length);
        assertEquals(16, hardPuzzle.getState().length);
    }
    
    @Test
    public void generatePuzzleTilesSumUp() {
        Puzzle easyPuzzle = this.service.generateRandomPuzzle(3);
        Puzzle hardPuzzle = this.service.generateRandomPuzzle(50);
        int easyValue = Arrays.stream(easyPuzzle.getState()).sum();
        int hardValue = Arrays.stream(hardPuzzle.getState()).sum();
        assertEquals(120, easyValue);
        assertEquals(120, hardValue);
    }
    
    @Test
    public void puzzleIsSolvable() {
        assertTrue(this.service.generateRandomPuzzle(2).isSolvable());
        assertTrue(this.service.generateRandomPuzzle(1234).isSolvable());
    }
    
}
