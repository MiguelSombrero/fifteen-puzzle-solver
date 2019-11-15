
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.HashSet;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Class that implements A* -algorithm.
 * 
 * @author miika
 */
public class AStar {
    
    /**
     * Method for solving n-puzzle with A* -algorithm. Algorithm uses Dijkstra's
     * breadth-first-method and uses heuristic which is passed as a comparator attribute.
     * 
     * @param game Puzzle to solve
     * @param comparator Comparator class which compares game states together with heuristic
     * @return Solved puzzle or null if it cannot be solved
     */
    public Puzzle traverse(Puzzle game, Comparator comparator) {
        if (!game.isSolvable()) {
            return null;
        }
        PriorityQueue<Puzzle> queue = new PriorityQueue<>(comparator);
        HashSet<Puzzle> visited = new HashSet<>();
        queue.add(game);
        
        while (!queue.isEmpty()) {
            Puzzle currentState = queue.poll();
            
            if (currentState.isSolved()) {
                return currentState;
            }
            
            if (visited.contains(currentState)) {
                continue;
            }
            
            visited.add(currentState);
            ArrayList<Puzzle> children = currentState.generateChildren();
            
            for (int i = 0; i < children.size(); i++) {
                if (children.get(i).isSolved()) {
                    return children.get(i);
                }
                
                if (!visited.contains(children.get(i))) {
                    queue.add(children.get(i));
                }
            }
        }
        return null;
    }
}
