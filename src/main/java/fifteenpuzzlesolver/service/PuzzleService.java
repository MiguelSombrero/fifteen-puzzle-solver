
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import java.util.Comparator;

/**
 * Class that provides services for solving and generating puzzles.
 * @author miika
 */
public class PuzzleService {
 
    private AStar astar;
    private PuzzleGenerator generator;
    private StateComparatorManhattan manhattan;
    private StateComparatorPosition position;
    private StateComparatorLinearCollision linear;
    
    /**
     * Constructor method.
     * @param astar Class that provides implementation of A* -algorithm
     * @param generator Utility class that provides methods for generating puzzles
     * @param manhattan Comparator class that uses manhattan heuristics for comparing puzzles
     * @param position Comparator class that uses position based heuristics for comparing puzzles
     * @param linear Comparator class that uses linear collision heuristics for comparing puzzles
     */
    public PuzzleService(AStar astar, PuzzleGenerator generator, StateComparatorManhattan manhattan,
            StateComparatorPosition position, StateComparatorLinearCollision linear) {
        this.astar = astar;
        this.generator = generator;
        this.manhattan = manhattan;
        this.position = position;
        this.linear = linear;
    }
    
    /**
     * Method which solves puzzle using A* and manhattan heuristics.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if puzzle is solvable. Null otherwise
     */
    public Puzzle solveWithManhattan(Puzzle puzzle) {
        return this.astar.traverse(puzzle, this.manhattan);
    }
    
    /**
     * Method which solves puzzle using A* and position based heuristics.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if puzzle is solvable. Null otherwise
     */
    public Puzzle solveWithPosition(Puzzle puzzle) {
        return this.astar.traverse(puzzle, this.position);
    }
    
    /**
     * Method which solves puzzle using A* and position based heuristics.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if puzzle is solvable. Null otherwise
     */
    public Puzzle solveWithLinearCollision(Puzzle puzzle) {
        return this.astar.traverse(puzzle, this.linear);
    }
    
    /**
     * Solves puzzle n times using A* and given heuristics. Used for calculating average
     * solving time for specific puzzle
     * 
     * @param puzzle Puzzle to solve
     * @param iterations Times puzzle is solved
     * @param comparator Heuristics used in A* -algorithm
     * @return Average solving time in milliseconds
     */
    private long benchmark(Puzzle puzzle, int iterations, Comparator comparator) {
        long overall = 0;
        
        for (int i = 0; i < iterations; i++) {
            long time1 = System.currentTimeMillis();
            this.astar.traverse(puzzle, comparator);
            long time2 = System.currentTimeMillis();
            overall += time2 - time1;
        }
        
        return overall / iterations;
    }
    
    /**
     * Benchmarks puzzle n times using A* manhattan algorithm.
     * 
     * @param puzzle Puzzle to Benchmark
     * @param iterations Times puzzle is solved
     * @return Average solving time in milliseconds
     */
    public long benchmarkAStarManhattan(Puzzle puzzle, int iterations) {
        return benchmark(puzzle, iterations, this.manhattan);
    }
    
    /**
     * Benchmarks puzzle n times using A* position algorithm.
     * 
     * @param puzzle Puzzle to Benchmark
     * @param iterations Times puzzle is solved
     * @return Average solving time in milliseconds
     */
    public long benchmarkAStarPosition(Puzzle puzzle, int iterations) {
        return benchmark(puzzle, iterations, this.position);
    }
    
    /**
     * Benchmarks puzzle n times using A* linear collision algorithm.
     * 
     * @param puzzle Puzzle to Benchmark
     * @param iterations Times puzzle is solved
     * @return Average solving time in milliseconds
     */
    public long benchmarkAStarLinearCollision(Puzzle puzzle, int iterations) {
        return benchmark(puzzle, iterations, this.linear);
    }
    
    /**
     * Method for generating 15-puzzle. Puzzle gets harder when shuffles increase
     * 
     * @param shuffles Number of shuffles from solved puzzle state
     * @return 15-puzzle
     */
    public Puzzle generatePuzzle(int shuffles) {
        return this.generator.generatePuzzle(shuffles);
    }
    
}
