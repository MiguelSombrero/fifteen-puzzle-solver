
package fifteenpuzzlesolver.domain;

import java.util.ArrayList;

public interface Puzzle {
    int moves();
    int emptyIndex();
    int[] state();
    
    void printBoard();
    
    ArrayList<Puzzle> generateStates();
    
    boolean isSolvable();
    
    boolean isSolved();
}
