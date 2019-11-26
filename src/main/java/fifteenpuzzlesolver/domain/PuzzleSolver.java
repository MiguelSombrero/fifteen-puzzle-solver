
package fifteenpuzzlesolver.domain;

/**
 * Interface for creating class that solves puzzle using some algorithm.
 * 
 * @author miika
 */
public interface PuzzleSolver {
    
    /**
     * Solves given puzzle.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if solvable, null otherwise
     */
    Puzzle solve(Puzzle puzzle);
}
