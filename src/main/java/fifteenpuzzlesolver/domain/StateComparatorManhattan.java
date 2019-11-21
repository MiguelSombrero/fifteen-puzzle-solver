
package fifteenpuzzlesolver.domain;

import java.util.Comparator;

/**
 * Class to compare two puzzles with A* and Manhattan heuristics.
 * 
 * @author miika
 */
public class StateComparatorManhattan implements Comparator<Puzzle>,  HeuristicCalculator {

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
     * Method for counting sum of Manhattan distances between tiles of given puzzle.
     * 
     * @param p Puzzle which Manhattan distance is being calculated
     * @return Sum of Manhattan distances between tiles of given puzzle
     */
    @Override
    public int heuristic(Puzzle p) {
        int value = 0;
        
        for (int i = 0; i < p.getState().length; i++) {
            if (p.getState()[i] != 0) {
                value += countManhattanDistance(i, p.getState()[i] - 1);
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
