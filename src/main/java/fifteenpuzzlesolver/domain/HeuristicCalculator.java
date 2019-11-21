
package fifteenpuzzlesolver.domain;

/**
 * Interface which defines classes for implementing heuristic for n-puzzles.
 * 
 * @author miika
 */
public interface HeuristicCalculator {
    
    /**
     * Calculates heuristic value for the given puzzle. Value is zero
     * if the puzzle is solved.
     * 
     * @param p Puzzle which heuristic value is calculated
     * @return Heuristic value of the puzzle
     */
    int heuristic(Puzzle p);
}
