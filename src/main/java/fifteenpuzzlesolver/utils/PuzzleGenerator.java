
package fifteenpuzzlesolver.utils;

import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.Puzzle;
import java.util.Random;

public class PuzzleGenerator {
    public Puzzle generatePuzzle() {
        Random r = new Random();
        
        while (true) {
            int[] state = new int[16];
        
            for (int i = 0; i < state.length-1; i++) {
                state[i] = i+1;
            }
        
            for (int i = 0; i < 100; i++) {
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
