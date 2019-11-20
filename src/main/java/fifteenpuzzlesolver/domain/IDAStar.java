
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.ArrayList;
import java.util.PriorityQueue;


/**
 *
 * @author miika
 */
public class IDAStar {
    
    private StateComparatorLinearCollision comparator;
    
    public IDAStar(StateComparatorLinearCollision comparator) {
        this.comparator = comparator;
    }
    
    public int search(PriorityQueue<Puzzle> queue, int bound) {
        Puzzle current = queue.peek();
        int estimate = current.getMoves() + comparator.heuristic(current);
        
        if (estimate > bound) {
            return estimate;
        }
        
        if (current.isSolved()) {
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        ArrayList<Puzzle> children = current.generateChildren();
            
        for (int i = 0; i < children.size(); i++) {
            if (!queue.contains(children.get(i))) {
                
                queue.add(children.get(i));
                int value = search(queue, bound);
                
                if (value == 0) {
                    return 0;
                }
                
                if (value < min) {
                    min = value;
                }
                
                queue.poll();
            }
        }
        
        return min;
    }
    
    public int idaStar(Puzzle puzzle) {
        int bound = comparator.heuristic(puzzle);
        PriorityQueue<Puzzle> queue = new PriorityQueue<>(comparator);
        queue.add(puzzle);
        
        while(true) {
            int value = search(queue, bound);
            
            if (value == 0) {
                return bound;
            }
            if (value == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            bound = value;
            
            System.out.println(bound);
        }
    }
}
