
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.HashSet;
import fifteenpuzzlesolver.utils.PriorityQueue;
import java.util.Comparator;

/**
 * Class that implements A* -algorithm.
 * 
 * @author miika
 */
public class AStar implements PuzzleSolver {
    
    private Comparator comparator;
    
    /**
     * Constructor class. Initializes comparator which is used to compare puzzles
     * in PriorityQueue.
     * 
     * @param comparator Comparator class for comparing puzzles
     */
    public AStar(Comparator comparator) {
        this.comparator = comparator;
    }
    
    /**
     * Method for traversing game tree using A* algorithm.
     * 
     * @param queue Puzzles in the queue not yet visited
     * @param visited Visited puzzles
     * @return Solved puzzle if solvable, null otherwise
     */
    public Puzzle search(PriorityQueue<Puzzle> queue, HashSet<Puzzle> visited) {
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
    
    /**
     * Method for solving n-puzzle with A* -algorithm. Algorithm uses Dijkstra's
     * breadth-first-method and uses heuristic which is passed as a comparator attribute.
     * 
     * @param game Puzzle to solve
     * @return Solved puzzle if solvable, null otherwise
     */
    @Override
    public Puzzle solve(Puzzle game) {
        if (!game.isSolvable()) {
            return null;
        }
        PriorityQueue<Puzzle> queue = new PriorityQueue<>(this.comparator);
        HashSet<Puzzle> visited = new HashSet<>();
        queue.add(game);
        return search(queue, visited);
    }
}
