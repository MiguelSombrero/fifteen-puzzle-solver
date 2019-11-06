
package fifteenpuzzlesolver.astar;

import fifteenpuzzlesolver.domain.Puzzle;
import java.util.Comparator;

/**
 * Class to compare two Puzzles with position-based heuristics.
 * 
 * @author miika
 */
public class StateComparatorPosition implements Comparator<Puzzle> {

    /**
     * Method for calculating heuristic value of the puzzle.
     * 
     * @param p Puzzle which value is calculated
     * 
     * @return Heuristic value of the puzzle. Value is same
     * as number of misplaced tiles on the puzzle
     */
    public int heuristic(Puzzle p) {
        int value = 0;
        
        for (int i = 0; i < p.state().length; i++) {
            if (p.state()[i] != 0 && p.state()[i] != i + 1) {
                value++;
            }
        }
        
        return value;
    }
    
    /**
     * Method which compares two puzzles. Comparison function uses
     * puzzles current value (moves) and estimated moves left (heuristic)
     * 
     * @param o1 Comparable puzzle no. 1
     * @param o2 Comparable puzzle no. 2
     * @return Value of the comparison
     */
    @Override
    public int compare(Puzzle o1, Puzzle o2) {
        return o1.moves() + heuristic(o1) - o2.moves() - heuristic(o2);
    }
    
}
