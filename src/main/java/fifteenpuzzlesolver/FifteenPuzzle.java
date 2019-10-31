
package fifteenpuzzlesolver;

import java.util.ArrayList;

public class FifteenPuzzle implements Puzzle {
    
    private int[] state;
    private int moves;
    
    public FifteenPuzzle(int[] state) {
        this.state = state;
        this.moves = 0;
    }
    
    public FifteenPuzzle(int[] state, int moves) {
        this.state = state;
        this.moves = moves;
    }

    @Override
    public int moves() {
        return this.moves;
    }
    
    @Override
    public int[] state() {
        return this.state;
    }

    @Override
    public ArrayList<Puzzle> generateStates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSolvable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSolved() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String toString() {
        return "";
    }
    
}
