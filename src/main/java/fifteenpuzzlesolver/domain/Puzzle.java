
package fifteenpuzzlesolver.domain;

import java.util.ArrayList;

/**
 * Interface for implementing n-puzzles.
 * @author miika
 */
public interface Puzzle {
    
    int moves();
    
    int[] state();
    
    ArrayList<Puzzle> generateChildren();
    
    boolean isSolvable();
    
    boolean isSolved();
}
