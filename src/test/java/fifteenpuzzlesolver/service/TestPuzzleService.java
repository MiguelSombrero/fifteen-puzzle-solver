
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.StateComparatorPosition;
import fifteenpuzzlesolver.domain.StateComparatorManhattan;
import fifteenpuzzlesolver.domain.StateComparatorLinearCollision;
import fifteenpuzzlesolver.domain.AStar;
import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.IDAStar;
import fifteenpuzzlesolver.domain.MockPuzzleSolver;
import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.domain.PuzzleSolver;
import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import fifteenpuzzlesolver.utils.TestUtils;
import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

// NOTICE: THIS CLASS USES MOCK PUZZLESOLVER WHICH SETS 1s TIMEOUT
// FOR EVERY solve() REQUEST. THEREFORE RUNNING THESE TESTS MAY TAKE
// SOME 30 SECONDS.

/**
 *
 * @author miika
 */
public class TestPuzzleService {
    
    private PuzzleService service;
    private PuzzleService mockedService;
    private ArrayList<int[]> boards;
    private PuzzleSolver mockSolver;
    
    public TestPuzzleService() {
        StateComparatorLinearCollision linear = new StateComparatorLinearCollision();
        StateComparatorManhattan manhattan = new StateComparatorManhattan();
        StateComparatorPosition position = new StateComparatorPosition();
        
        Random random = new Random();
        PuzzleGenerator generator = new PuzzleGenerator(random);
        TestUtils utils = new TestUtils();
        
        this.boards = utils.boardList();
        this.mockSolver = new MockPuzzleSolver();
        
        this.mockedService = new PuzzleService(generator, new MockPuzzleSolver(), new MockPuzzleSolver(),
            new MockPuzzleSolver(), new MockPuzzleSolver(), new MockPuzzleSolver(), new MockPuzzleSolver());
        
        this.service = new PuzzleService(generator, new AStar(position), new AStar(manhattan),
            new AStar(linear), new IDAStar(position), new IDAStar(manhattan), new IDAStar(linear));
    }
    
    @Test
    public void solveAReturnsNullIfUnsolvable() {
        assertEquals(null, this.service.solveWithAPosition(new FifteenPuzzle(this.boards.get(1))));
        assertEquals(null, this.service.solveWithAManhattan(new FifteenPuzzle(this.boards.get(1))));
        assertEquals(null, this.service.solveWithALinearCollision(new FifteenPuzzle(this.boards.get(1))));
    }
    
    @Test
    public void solveIDAReturnsNullIfUnsolvable() {
        assertEquals(null, this.service.solveWithIDAPosition(new FifteenPuzzle(this.boards.get(1))));
        assertEquals(null, this.service.solveWithIDAManhattan(new FifteenPuzzle(this.boards.get(1))));
        assertEquals(null, this.service.solveWithIDALinearCollision(new FifteenPuzzle(this.boards.get(1))));
    }
    
    @Test
    public void solveAReturnsSolvedPuzzleIfSolvable() {
        assertArrayEquals(this.boards.get(0), this.service.solveWithAManhattan(new FifteenPuzzle(this.boards.get(2))).getState());
        assertArrayEquals(this.boards.get(0), this.service.solveWithAPosition(new FifteenPuzzle(this.boards.get(3))).getState());
        assertArrayEquals(this.boards.get(0), this.service.solveWithALinearCollision(new FifteenPuzzle(this.boards.get(4))).getState());
    }
    
    @Test
    public void solveIDAReturnsSolvedPuzzleIfSolvable() {
        assertArrayEquals(this.boards.get(0), this.service.solveWithIDAManhattan(new FifteenPuzzle(this.boards.get(2))).getState());
        assertArrayEquals(this.boards.get(0), this.service.solveWithIDAPosition(new FifteenPuzzle(this.boards.get(3))).getState());
        assertArrayEquals(this.boards.get(0), this.service.solveWithIDALinearCollision(new FifteenPuzzle(this.boards.get(4))).getState());
    }
    
    @Test
    public void benchmarkCalculatesAverageMovesRight() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new FifteenPuzzle(this.boards.get(2), 12));
        puzzles.add(new FifteenPuzzle(this.boards.get(3), 5));
        puzzles.add(new FifteenPuzzle(this.boards.get(4), 2));
        puzzles.add(new FifteenPuzzle(this.boards.get(5), 7));
        
        assertEquals(6, this.service.benchmark(puzzles, mockSolver)[1]);
    }
    
    @Test
    public void benchmarkCalculatesAverageTimeRight() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new FifteenPuzzle(this.boards.get(2), 12));
        puzzles.add(new FifteenPuzzle(this.boards.get(3), 5));
        puzzles.add(new FifteenPuzzle(this.boards.get(4), 2));
        puzzles.add(new FifteenPuzzle(this.boards.get(5), 7));
        
        assertEquals(1000.0, this.service.benchmark(puzzles, mockSolver)[0], 5.0);
    }
    
    @Test
    public void benchmarkAPositionReturnsRightValues() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new FifteenPuzzle(this.boards.get(2), 2));
        puzzles.add(new FifteenPuzzle(this.boards.get(3), 1));
        
        assertEquals(1, this.mockedService.benchmarkAStarPosition(puzzles)[1]);
        assertEquals(1000.0, this.mockedService.benchmarkAStarPosition(puzzles)[0], 5.0);
    }
    
    @Test
    public void benchmarkAManhattanReturnsRightValues() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new FifteenPuzzle(this.boards.get(2), 11));
        puzzles.add(new FifteenPuzzle(this.boards.get(3), 1));
        puzzles.add(new FifteenPuzzle(this.boards.get(4), 1));
        
        assertEquals(4, this.mockedService.benchmarkAStarManhattan(puzzles)[1]);
        assertEquals(1000.0, this.mockedService.benchmarkAStarManhattan(puzzles)[0], 5.0);
    }
    
    @Test
    public void benchmarkALinearReturnsRightValues() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new FifteenPuzzle(this.boards.get(2), 2));
        puzzles.add(new FifteenPuzzle(this.boards.get(3), 2));
        puzzles.add(new FifteenPuzzle(this.boards.get(4), 1));
        
        assertEquals(1, this.mockedService.benchmarkAStarLinearCollision(puzzles)[1]);
        assertEquals(1000.0, this.mockedService.benchmarkAStarLinearCollision(puzzles)[0], 5.0);
    }
    
    @Test
    public void benchmarkIDAPositionReturnsRightValues() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new FifteenPuzzle(this.boards.get(2), 123));
        
        assertEquals(123, this.mockedService.benchmarkIDAStarPosition(puzzles)[1]);
        assertEquals(1000.0, this.mockedService.benchmarkIDAStarPosition(puzzles)[0], 5.0);
    }
    
    @Test
    public void benchmarkIDAManhattanReturnsRightValues() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new FifteenPuzzle(this.boards.get(2), 11));
        puzzles.add(new FifteenPuzzle(this.boards.get(3), 1));
        puzzles.add(new FifteenPuzzle(this.boards.get(4), 15));
        puzzles.add(new FifteenPuzzle(this.boards.get(4), 1));
        
        assertEquals(7, this.mockedService.benchmarkIDAStarManhattan(puzzles)[1]);
        assertEquals(1000.0, this.mockedService.benchmarkIDAStarManhattan(puzzles)[0], 5.0);
    }
    
    @Test
    public void benchmarkIDALinearReturnsRightValues() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(new FifteenPuzzle(this.boards.get(2), 2));
        puzzles.add(new FifteenPuzzle(this.boards.get(3), 2));
        puzzles.add(new FifteenPuzzle(this.boards.get(4), 2));
        
        assertEquals(2, this.mockedService.benchmarkIDAStarLinearCollision(puzzles)[1]);
        assertEquals(1000.0, this.mockedService.benchmarkIDAStarLinearCollision(puzzles)[0], 5.0);
    }
    
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
