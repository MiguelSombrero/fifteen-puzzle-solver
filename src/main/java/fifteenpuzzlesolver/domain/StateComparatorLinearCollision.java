
package fifteenpuzzlesolver.domain;

import java.util.Comparator;

/**
 * Class to compare two puzzles with A* and Manhattan distance + Linear Collision heuristics.
 * 
 * @author miika
 */
public class StateComparatorLinearCollision implements Comparator<Puzzle> {
    
    /**
     * Method for counting Manhattan distance between tiles current and correct position.
     * 
     * @param current Index of current tile
     * @param correct Index of current tiles correct place
     * @return Manhattan distance between tiles current and correct position
     */
    public int countManhattanDistance(int current, int correct) {
        int goalY = correct / 4;
        int goalX = correct % 4;
        int currentY = current / 4;
        int currentX = current % 4;
        
        return Math.abs(goalY - currentY) + Math.abs(goalX - currentX);
    }
    
    /**
     * Checks if a specific values (tiles) is in the same ROW.
     * 
     * @param i Index of tile no 1.
     * @param j Index of tile no 2.
     * @return True if values are in the same row, false otherwise
     */
    public boolean isInSameRow(int i, int j) {
        return i / 4 == j / 4;
    }
    
    /**
     * Checks if a specific values (tiles) is in the same COLUMN.
     * 
     * @param i Index of tile no 1.
     * @param j Index of tile no 2.
     * @return True if values are in the same column, false otherwise
     */
    public boolean isInSameColumn(int i, int j) {
        return i % 4 == j % 4;
    }
    
    /**
     * Checks if two tiles collide. They can collide by being in the same row
     * or in the same column. If they collide, value of the game state is incremented by two
     * 
     * @param state Game state 
     * @param i Index of tile no 1.
     * @param j Index of tile no 2.
     * @return Value added to the game state. Two if tiles collide, zero otherwise
     */
    public int countCollision(int[] state, int i, int j) {
        if (state[j] < state[i] && isInSameRow(i, j) && isInSameRow(state[i] - 1, i) && isInSameRow(state[j] - 1, j)) {
            return 2;
        }
        if (state[j] < state[i] && isInSameColumn(i, j) && isInSameColumn(state[i] - 1, i) && isInSameColumn(state[j] - 1, j)) {
            return 2;
        }
        return 0;
    }
    
    /**
     * Method for counting sum of Manhattan distances and linear collision
     * between tiles of given puzzle.
     * 
     * @param p Puzzle which heuritics is being calculated
     * @return Heuristic value of the puzzle
     */
    public int heuristic(Puzzle p) {
        int value = 0;
        
        for (int i = 0; i < p.getState().length; i++) {
            if (p.getState()[i] != 0) {
                value += countManhattanDistance(i, p.getState()[i] - 1);
            }
            
            for (int j = i; j < p.getState().length; j++) {
                if (p.getState()[i] != 0 && p.getState()[j] != 0) {
                    value += countCollision(p.getState(), i, j);
                }
            }
        }
        
        return value;
    }
    
    /**
     * Method for comparing two puzzles order.
     * 
     * @param o1 First puzzle to compare
     * @param o2 Second puzzle to compare
     * @return Integer value of order of the puzzles
     */
    @Override
    public int compare(Puzzle o1, Puzzle o2) {
        return o1.getMoves() + heuristic(o1) - o2.getMoves() - heuristic(o2);
    }
}
