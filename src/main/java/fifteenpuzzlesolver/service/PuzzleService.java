
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.StateComparatorManhattan;
import fifteenpuzzlesolver.domain.StateComparatorPosition;
import fifteenpuzzlesolver.domain.StateComparatorLinearCollision;
import fifteenpuzzlesolver.domain.AStar;
import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import java.util.Comparator;

/**
 * Class which provides services for solving and generating puzzles.
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
     * Solves n puzzles using A* and given heuristics. Used for calculating average
     * solving time for specific puzzle
     * 
     * @param puzzles Puzzles to solve for bencmarking
     * @param comparator Heuristics used in A* -algorithm
     * @return data consisting average solving time in milliseconds and average moves
     */
    private long[] benchmark(ArrayList<Puzzle> puzzles, Comparator comparator) {
        long data[] = new long[2];
        
        for (int i = 0; i < puzzles.size(); i++) {
            long time1 = System.currentTimeMillis();
            Puzzle solved = this.astar.traverse(puzzles.get(i), comparator);
            long time2 = System.currentTimeMillis();
            data[0] += time2 - time1;
            data[1] += solved.getMoves();
        }
        
        data[0] = data[0] / puzzles.size();
        data[1] = data[1] / puzzles.size();
        
        return data;
    }
    
    /**
     * Benchmarks puzzle n times using A* manhattan algorithm.
     * 
     * @param puzzles Puzzles to solve for Benchmarking
     * @return data consitig average solving time in milliseconds and average moves
     */
    public long[] benchmarkAStarManhattan(ArrayList<Puzzle> puzzles) {
        return benchmark(puzzles, this.manhattan);
    }
    
    /**
     * Benchmarks puzzle n times using A* position algorithm.
     * 
     * @param puzzles puzzles Puzzles to solve for Benchmarking
     * @return data consitig average solving time in milliseconds and average moves
     */
    public long[] benchmarkAStarPosition(ArrayList<Puzzle> puzzles) {
        return benchmark(puzzles, this.position);
    }
    
    /**
     * Benchmarks puzzle n times using A* linear collision algorithm.
     * 
     * @param puzzles Puzzles to solve for Benchmarking
     * @return data consitig average solving time in milliseconds and average moves
     */
    public long[] benchmarkAStarLinearCollision(ArrayList<Puzzle> puzzles) {
        return benchmark(puzzles, this.linear);
    }
    
    /**
     * Method for generating random 15-puzzle. Puzzle (usually) gets harder when shuffles increase
     * 
     * @param shuffles Number of shuffles from solved puzzle state
     * @return 15-puzzle
     */
    public Puzzle generateRandomPuzzle(int shuffles) {
        return this.generator.generateRandomPuzzle(shuffles);
    }
    
    /**
     * Method for generating 15-puzzle. Puzzle gets harder when moves increase
     * because it moves further away from solved state
     * 
     * @param moves Moves from solved state to random direction
     * @return 15-puzzle
     */
    public Puzzle generatePuzzleByMoves(int moves) {
        return this.generator.generatePuzzleByMoves(moves);
    }
    
    /**
     * Generates list of puzzles.
     * 
     * @param puzzles Number of puzzles to generate
     * @param moves Moves used for generating each puzzle
     * @return List of generated puzzles
     */
    public ArrayList<Puzzle> generateMultiplePuzzles(int puzzles, int moves) {
        ArrayList<Puzzle> p = new ArrayList<>();
        
        while(p.size() < puzzles) {
            Puzzle puzzle = generatePuzzleByMoves(moves);
            if (puzzle.isSolvable()) {
                p.add(puzzle);
            }
        }
        
        return p;
    }
}
