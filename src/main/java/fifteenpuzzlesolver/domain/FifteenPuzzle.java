
package fifteenpuzzlesolver.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class FifteenPuzzle implements Puzzle {
    
    private int[] state;
    private int moves;
    private int emptyIndex;
    
    public FifteenPuzzle(int[] state) {
        this.state = state;
        this.moves = 0;
        this.emptyIndex = 15;
    }
    
    public FifteenPuzzle(int[] state, int moves, int emptyIndex) {
        this.state = state;
        this.moves = moves;
        this.emptyIndex = emptyIndex;
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
    public int emptyIndex() {
        return this.emptyIndex;
    }
    
    public Puzzle move(int index) {
        int[] newState = this.state.clone();
        newState[this.emptyIndex] = newState[this.emptyIndex + index];
        newState[this.emptyIndex + index] = 0;
        
        return new FifteenPuzzle(newState, this.moves + 1, this.emptyIndex + index);
    }
    
    @Override
    public ArrayList<Puzzle> generateStates() {
        ArrayList<Puzzle> states = new ArrayList<>();
        
        if (this.emptyIndex + 1 < this.state.length) {
            states.add(move(1));
        }
        if (this.emptyIndex - 1 >= 0) {
            states.add(move(-1));
        }
        if (this.emptyIndex + 4 < this.state.length) {
            states.add(move(4));
        }
        if (this.emptyIndex - 4 >= 0) {
            states.add(move(-4));
        }
        
        return states;
    }
    
    public int inversions() {
        int inversions = 0;
        
        for (int i = 0; i < this.state.length; i++) {
            for (int j = i; j < this.state.length; j++) {
                if (this.state[i] != 0 && this.state[j] != 0 && this.state[i] > this.state[j]) {
                    inversions++;
                }
            }
        }
        
        return inversions;
    }

    @Override
    public boolean isSolvable() {
        if (this.isSolved()) {
            return true;
        }
        
        if (((this.state.length - this.emptyIndex) % 4) % 2 == 1) {
            if (inversions() % 2 == 1) {
                return true;
            }
            return false;
        }
        
        if (inversions() % 2 == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSolved() {
        for (int i = 0; i < this.state.length-1; i++) {
            if (this.state[i] != i+1) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "Moves: " + this.moves;
    }
    
    public void printBoard() {
        for (int i = 0; i < this.state.length; i++) {
            System.out.print(this.state[i] + "\t");
            
            if ((i + 1) % 4 == 0) {
                System.out.println("");
            }
        }
        System.out.println("");
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Arrays.hashCode(this.state);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FifteenPuzzle other = (FifteenPuzzle) obj;
        if (!Arrays.equals(this.state, other.state)) {
            return false;
        }
        return true;
    }
    
}
