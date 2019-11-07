
package fifteenpuzzlesolver.domain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that implements Puzzle interface and provides methods for handling 15-puzzle.
 * 
 * @author miika
 */
public class FifteenPuzzle implements Puzzle {
    
    private int[] state;
    private int moves;
    private int emptyIndex;
    
    /**
     * Constructor method.
     * 
     * @param state Initial game state
     */
    public FifteenPuzzle(int[] state) {
        this.state = state;
        this.moves = 0;
        this.emptyIndex = findEmpty();
    }
    
    /**
     * Constructor method.
     * 
     * @param state Game state
     * @param moves Moves from initial state to this state
     */
    public FifteenPuzzle(int[] state, int moves) {
        this.state = state;
        this.moves = moves;
        this.emptyIndex = findEmpty();
    }
    
    /**
     * Method which finds zero e.g. empty tile from game state
     * 
     * @return Index of empty tile
     */
    public int findEmpty() {
        for (int i = 0; i < this.state.length; i++) {
            if (this.state[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method which returns moves from initial state to this state.
     * @return Moves
     */
    @Override
    public int moves() {
        return this.moves;
    }
    
    /**
     * Method which return this game state e.g. board of the game
     * @return Game state
     */
    @Override
    public int[] state() {
        return this.state;
    }
    
    /**
     * Method which returns empty tile of this board.
     * @return Index of empty tile
     */
    public int emptyIndex() {
        return this.emptyIndex;
    }
    
    /**
     * Method which creates new 15-puzzle. Method moves empty tile according to given index
     * and increments new puzzle moves by one
     * 
     * @param index Value of transition with empty tile
     * @return New 15-puzzle
     */
    public FifteenPuzzle createChildren(int index) {
        int[] newState = this.state.clone();
        newState[this.emptyIndex] = newState[this.emptyIndex + index];
        newState[this.emptyIndex + index] = 0;
        
        return new FifteenPuzzle(newState, this.moves + 1);
    }
    
    /**
     * Method which generates list of all the possible childrens (transitions) from current
     * game state.
     * 
     * @return List of puzzles derived from current puzzle
     */
    @Override
    public ArrayList<Puzzle> generateChildren() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        
        if (this.emptyIndex + 1 < this.state.length) {
            puzzles.add(createChildren(1));
        }
        if (this.emptyIndex - 1 >= 0) {
            puzzles.add(createChildren(-1));
        }
        if (this.emptyIndex + 4 < this.state.length) {
            puzzles.add(createChildren(4));
        }
        if (this.emptyIndex - 4 >= 0) {
            puzzles.add(createChildren(-4));
        }
        
        return puzzles;
    }
    
    /**
     * Method for counting inversions of this game state. Inversions is used in isSolvable()
     * method for deducting is this game possible to solve
     * 
     * @return Count for inversions of this game
     */
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

    /**
     * Method for checking is this puzzle solvable. Algorithm is based on inversions
     * and is explained here:
     * https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/
     * 
     * @return True if puzzle can be solved. False if puzzle is unsolvable
     */
    @Override
    public boolean isSolvable() {
        if (this.isSolved()) {
            return true;
        }
        
        if (((this.state.length - this.emptyIndex - 1) / 4) % 2 == 1) {
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

    /**
     * Method for checking is this puzzle solved. Puzzle is solved if its tiles are
     * in natural order and empty tile is in index 15
     * 
     * @return True if puzzle is solved. False if it is not solved
     */
    @Override
    public boolean isSolved() {
        for (int i = 0; i < this.state.length - 1; i++) {
            if (this.state[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns this puzzle printed as a game board.
     * @return String of the game board
     */
    public String toString() {
        String puzzle = "";
        
        for (int i = 0; i < this.state.length; i++) {
            puzzle += this.state[i] + "\t";
            
            if ((i + 1) % 4 == 0) {
                puzzle += "\n";
            }
        }
        
        return puzzle;
    }
    
    /**
     * Method which generates hascode for this puzzle.
     * @return Hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Arrays.hashCode(this.state);
        return hash;
    }

    /**
     * Method which checks if current puzzle equals e.g. is same as given in parameter.
     * Two puzzles is considered to be equal, if all tiles of their game state is in
     * same position
     * 
     * @param obj Puzzle to be compared to current
     * @return True if puzzles equals. False if not
     */
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
