
package fifteenpuzzlesolver;

import java.util.ArrayList;

public interface Puzzle {
    int moves();
    
    int[] state();
    
    ArrayList<Puzzle> generateStates();
    
    boolean isSolvable();
    
    boolean isSolved();
}
