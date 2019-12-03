
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.Stack;

/**
 * Class which implements IDA* algorithm for solving n-puzzles.
 * @author miika
 */
public class IDAStar implements PuzzleSolver {
    
    private HeuristicCalculator calculator;
    
    /**
     * Constructor method. Initializes comparator to use calculating heuristics
     * 
     * @param calculator HeuristicCalculator class which is used to compare puzzles
     */
    public IDAStar(HeuristicCalculator calculator) {
        this.calculator = calculator;
    }
    
    /**
     * Cost function to calculate estimated distance between given puzzle
     * and solved state.
     * 
     * @param puzzle Puzzle which distance to calculate
     * @return Estimated distance from solved state
     */
    public int cost(Puzzle puzzle) {
        return puzzle.getMoves() + calculator.heuristic(puzzle);
    }
    
    /**
     * Method for traversing game tree using IDA* algorithm.
     * 
     * @param path Current path e.g. game states algorithm has traversed
     * @param bound Maximum estimated cost that is used for pruning nodes
     * @return Solved puzzle if solvable, null otherwise
     */
    public Puzzle search(Stack<Puzzle> path, int bound) {
        Puzzle current = path.peek();
        
        if (cost(current) > bound || current.getMoves() > 80 || current.isSolved()) {
            return current;
        }
        
        int min = Integer.MAX_VALUE;
        Puzzle best = null;
        ArrayList<Puzzle> children = current.generateChildren();
            
        for (int i = 0; i < children.size(); i++) {
            Puzzle child = children.get(i);
            
            if (child.isSolved()) {
                return child;
            }
                
            if (!path.contains(child)) {
                path.push(child);
                
                Puzzle child2 = search(path, bound);
                
                if (child2 != null && cost(child2) < min) {
                    min = cost(child2);
                    best = child2;
                }
                
                path.pop();
            }
            
        }
        
        return best;
    }
    
    /**
     * Method for solving n-puzzle with IDA* -algorithm. Algorithm uses iterative
     * deapth-first-method and uses heuristics to pruning less promising nodes.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if solvable, null otherwise
     */
    @Override
    public Puzzle solve(Puzzle puzzle) {
        if (!puzzle.isSolvable()) {
            return null;
        }
        
        Stack<Puzzle> path = new Stack<>();
        path.push(puzzle);
        int bound = cost(puzzle);
        
        while (true) {
            Puzzle current = search(path, bound);
            
            if (current.isSolved()) {
                return current;
            }
            
            bound = cost(current);
        }
    }
}
