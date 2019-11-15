
package fifteenpuzzlesolver.utils;

import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.Puzzle;
import java.util.Random;

/**
 * Utility class for generating 15-puzzles.
 * 
 * @author miika
 */
public class PuzzleGenerator {
    
    private Random r;
    
    /**
     * Constructor class. Initializes new random generator
     * 
     * @param random Random generator
     */
    public PuzzleGenerator(Random random) {
        this.r = random;
    }
    
    private Puzzle generateChildren(Puzzle currentPuzzle, HashSet<Puzzle> visited) {
        ArrayList<Puzzle> children = currentPuzzle.generateChildren();
        
        while (true) {
            int index = r.nextInt(children.size());
            
            if (!visited.contains(children.get(index))) {
                visited.add(children.get(index));
                return children.get(index);
            }
        }
    }
    
    private Puzzle traverseGameTree(int moves, Puzzle currentPuzzle, HashSet<Puzzle> visited) {
        if (moves == 0) {
            return currentPuzzle;
        }
        visited.add(currentPuzzle);
        Puzzle child = generateChildren(currentPuzzle, visited);
        return traverseGameTree(moves-1, child, visited);
    }
    
    public Puzzle generatePuzzleByMoves(int moves) {
        int[] initialState = {
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 0
        };
        
        HashSet<Puzzle> visited = new HashSet<>();
        Puzzle initialPuzzle = new FifteenPuzzle(initialState);
        return traverseGameTree(moves-1, initialPuzzle, visited);
    }
    
    /**
     * Method for generating random puzzles. Puzzle (usually) gets harder when suffles increase
     * 
     * @param suffles Number of two random tiles that are switched
     * @return 15-puzzle
     */
    public Puzzle generateRandomPuzzle(int suffles) {
        while (true) {
            int[] state = new int[16];
        
            for (int i = 0; i < state.length - 1; i++) {
                state[i] = i + 1;
            }
        
            for (int i = 0; i < suffles; i++) {
                int a = r.nextInt(15);
                int b = r.nextInt(15);
                int temp = state[a];
                state[a] = state[b];
                state[b] = temp;
            }
            
            FifteenPuzzle puzzle = new FifteenPuzzle(state);
            
            if (puzzle.isSolvable()) {
                return puzzle;
            }
        }
    }
}
