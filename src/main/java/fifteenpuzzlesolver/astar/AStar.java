
package fifteenpuzzlesolver.astar;

import fifteenpuzzlesolver.domain.Puzzle;
import java.util.Comparator;
import java.util.HashSet;
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
        PriorityQueue queue = new PriorityQueue<>(comparator);
        HashSet<Puzzle> visited = new HashSet<>();
        
        queue.add(game);
        
        while (!queue.isEmpty()) {
            Puzzle currentState = (Puzzle) queue.poll();
            
//            System.out.println("Game state: ");
//            currentState.printBoard();
            
            // System.out.println(currentState.toString());
            
            if (currentState.isSolved()) {
                return currentState;
            }
            
            if (visited.contains(currentState) || !currentState.isSolvable()) {
                continue;
            }
            
            visited.add(currentState);
            
            for (Puzzle childState : currentState.generateStates()) {
                queue.add(childState);
            }
        }
        return null;
    }
}
