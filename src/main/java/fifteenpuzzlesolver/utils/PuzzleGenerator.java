
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
    
    /**
     * Method for generating easy 15-puzzle. Puzzle have been suffled only two times
     * @return 15-puzzle
     */
    public Puzzle generateEasyPuzzle() {
        return generatePuzzle(2);
    }
    
    /**
     * Method for generating hard 15-puzzle. Puzzle have been suffled 100 times
     * @return 15-puzzle
     */
    public Puzzle generateHardPuzzle() {
        return generatePuzzle(100);
    }
    
    /**
     * Method for generating puzzles. Puzzle gets harder when suffles increase
     * 
     * @param suffles Number of two random tiles that are switched
     * @return 15-puzzle
     */
    public Puzzle generatePuzzle(int suffles) {
        Random r = new Random();
        
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
