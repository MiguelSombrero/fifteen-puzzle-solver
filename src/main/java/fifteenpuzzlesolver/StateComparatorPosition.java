
package fifteenpuzzlesolver;

import java.util.Comparator;

public class StateComparatorPosition implements Comparator<FifteenPuzzle> {

    public int heuristic(FifteenPuzzle f) {
        return 0;
    }
    
    @Override
    public int compare(FifteenPuzzle o1, FifteenPuzzle o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
