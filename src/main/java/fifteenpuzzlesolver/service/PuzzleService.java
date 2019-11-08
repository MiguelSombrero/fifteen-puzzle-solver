
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.utils.PuzzleGenerator;

/**
 * Class that provides services for solving and generating puzzles.
 * @author miika
 */
public class PuzzleService {
 
    private AStar astar;
    private PuzzleGenerator generator;
    private StateComparatorManhattan manhattan;
    private StateComparatorPosition position;
    
    /**
     * Constructor method.
     * @param astar Class that provides implementation of A* -algorithm
     * @param generator Utility class that provides methods for generating puzzles
     * @param manhattan Comparator class that uses manhattan heuristics for comparing puzzles
     * @param position Comparator class that uses position based heuristics for comparing puzzles
     */
    public PuzzleService(AStar astar, PuzzleGenerator generator, StateComparatorManhattan manhattan,
            StateComparatorPosition position) {
        this.astar = astar;
        this.generator = generator;
        this.manhattan = manhattan;
        this.position = position;
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
     * Method for generating easy 15-puzzle. Puzzle have been shuffled 3 times
     * @return 15-puzzle
     */
    public Puzzle generateEasyPuzzle() {
        return this.generator.generatePuzzle(3);
    }
    
    /**
     * Method for generating hard 15-puzzle. Puzzle have been shuffled 100 times
     * @return 15-puzzle
     */
    public Puzzle generateHardPuzzle() {
        return this.generator.generatePuzzle(100);
    }
}
