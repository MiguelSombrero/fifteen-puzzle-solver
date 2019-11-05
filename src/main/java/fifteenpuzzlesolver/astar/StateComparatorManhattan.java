
package fifteenpuzzlesolver.astar;

import fifteenpuzzlesolver.domain.Puzzle;
import java.util.Comparator;

public class StateComparatorManhattan implements Comparator<Puzzle> {

    public int countDistance(int i, int tile) {
        int goalY = tile / 4;
        int goalX = tile % 4;
        int currentY = i / 4;
        int currentX = i % 4;
            
        return Math.abs(goalY - currentY) + Math.abs(goalX - currentX);
    }
    
    public int heuristic(Puzzle p) {
        int value = 0;
        
        for (int i = 0; i < p.state().length-1; i++) {
            value += countDistance(i, p.state()[i] - 1);
        }
        
        return value;
    }
    
    @Override
    public int compare(Puzzle o1, Puzzle o2) {
        return o1.moves() + heuristic(o1) - o2.moves() - heuristic(o2);
    }

    
}
