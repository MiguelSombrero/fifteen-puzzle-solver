
package fifteenpuzzlesolver.domain;

import fifteenpuzzlesolver.utils.ArrayList;

/**
 * Interface for implementing n-puzzles.
 * @author miika
 */
public interface Puzzle {
    
    /**
     * Returns moves from initial state to this state.
     * 
     * @return Moves
     */
    int moves();
    
    /**
     * Returns game state e.g. board of the game
     * @return Game state
     */
    int[] state();
    
    /**
     * Generates list of all the possible childrens (transitions) from current
     * game state.
     * 
     * @return List of children game states
     */
    ArrayList<Puzzle> generateChildren();
    
    /**
     * Method for checking is this puzzle solvable.
     * 
     * @return True if puzzle can be solved. False if puzzle is unsolvable
     */
    boolean isSolvable();
    
    /**
     * Method for checking is this puzzle solved.
     * 
     * @return True if puzzle is solved. False if it is not solved
     */
    boolean isSolved();
}
