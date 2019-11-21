
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.StateComparatorPosition;
import fifteenpuzzlesolver.domain.StateComparatorManhattan;
import fifteenpuzzlesolver.domain.StateComparatorLinearCollision;
import fifteenpuzzlesolver.domain.AStar;
import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.IDAStar;
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
        IDAStar idaStar = new IDAStar(linear);
        
        this.boards = utils.boardList();
        this.service = new PuzzleService(astar, generator, manhattan, position, linear, idaStar);
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
    public void solveLinearCollisionReturnsNullIfUnsolvable() {
        assertEquals(null, this.service.solveWithLinearCollision(new FifteenPuzzle(this.boards.get(1))));
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
    public void solveLinearCollisionReturnsSolvedPuzzleIfSolvable() {
        assertArrayEquals(this.boards.get(0), this.service.solveWithLinearCollision(new FifteenPuzzle(this.boards.get(2))).getState());
    }
    
    // tähän benchmark testejä
    
    @Test
    public void generateRandomPuzzleIsFifteenPuzzle() {
        Puzzle easyPuzzle = this.service.generateRandomPuzzle(2);
        Puzzle hardPuzzle = this.service.generateRandomPuzzle(100);
        assertEquals(16, easyPuzzle.getState().length);
        assertEquals(16, hardPuzzle.getState().length);
    }
    
    @Test
    public void generatePuzzleByMovesIsFifteenPuzzle() {
        Puzzle p1 = this.service.generatePuzzleByMoves(5);
        Puzzle p2 = this.service.generatePuzzleByMoves(50);
        Puzzle p3 = this.service.generatePuzzleByMoves(500);
        Puzzle p4 = this.service.generatePuzzleByMoves(1000);
        assertEquals(16, p1.getState().length);
        assertEquals(16, p2.getState().length);
        assertEquals(16, p3.getState().length);
        assertEquals(16, p4.getState().length);
    }
    
    @Test
    public void generateRandomPuzzleTilesSumUp() {
        Puzzle easyPuzzle = this.service.generateRandomPuzzle(3);
        Puzzle hardPuzzle = this.service.generateRandomPuzzle(50);
        int easyValue = Arrays.stream(easyPuzzle.getState()).sum();
        int hardValue = Arrays.stream(hardPuzzle.getState()).sum();
        assertEquals(120, easyValue);
        assertEquals(120, hardValue);
    }
    
    @Test
    public void generatePuzzleByMovesTilesSumUp() {
        Puzzle p1 = this.service.generatePuzzleByMoves(5);
        Puzzle p2 = this.service.generatePuzzleByMoves(50);
        Puzzle p3 = this.service.generatePuzzleByMoves(500);
        Puzzle p4 = this.service.generatePuzzleByMoves(1000);
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
    public void RandomPuzzleIsSolvable() {
        assertTrue(this.service.generateRandomPuzzle(2).isSolvable());
        assertTrue(this.service.generateRandomPuzzle(1234).isSolvable());
    }
    
    @Test
    public void puzzleByMovesIsSolvable() {
        assertTrue(this.service.generatePuzzleByMoves(3).isSolvable());
        assertTrue(this.service.generatePuzzleByMoves(50).isSolvable());
        assertTrue(this.service.generatePuzzleByMoves(100).isSolvable());
        assertTrue(this.service.generatePuzzleByMoves(256).isSolvable());
        assertTrue(this.service.generatePuzzleByMoves(1000).isSolvable());
        assertTrue(this.service.generatePuzzleByMoves(999).isSolvable());
    }
    
    @Test
    public void generateMultiplePuzzlesGeneratesRightAmountOfPuzzles() {
        ArrayList<Puzzle> p1 = this.service.generateMultiplePuzzles(5, 10);
        ArrayList<Puzzle> p2 = this.service.generateMultiplePuzzles(10, 20);
        ArrayList<Puzzle> p3 = this.service.generateMultiplePuzzles(100, 30);
        assertEquals(5, p1.size());
        assertEquals(10, p2.size());
        assertEquals(100, p3.size());
    }
    
    @Test
    public void generateMultiplePuzzlesAreSolvable() {
        ArrayList<Puzzle> puzzles = this.service.generateMultiplePuzzles(10, 10);
        
        for (int i = 0; i < puzzles.size(); i++) {
            assertTrue(puzzles.get(i).isSolvable());
        }
    }
    
    @Test
    public void generateMultiplePuzzlesMovesIsZero() {
        ArrayList<Puzzle> puzzles = this.service.generateMultiplePuzzles(10, 10);
        
        for (int i = 0; i < puzzles.size(); i++) {
            assertEquals(0, puzzles.get(i).getMoves());
        }
    }
}
