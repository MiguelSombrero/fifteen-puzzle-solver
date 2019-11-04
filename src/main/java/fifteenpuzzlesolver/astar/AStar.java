
package fifteenpuzzlesolver.astar;

import fifteenpuzzlesolver.domain.Puzzle;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
    public Puzzle traverse(Puzzle game, Comparator comparator) {
        PriorityQueue queue = new PriorityQueue<>(comparator);
        HashSet<Puzzle> visited = new HashSet<>();
        
        queue.add(game);
        
        while(!queue.isEmpty()) {
            Puzzle currentState = (Puzzle) queue.poll();
            
//            System.out.println("Game state: ");
//            currentState.printBoard();
            
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
