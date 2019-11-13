
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.Puzzle;
import java.util.Comparator;

/**
 * Class to compare two puzzles with A* and Linear Collision heuristics
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
    
    public int countLinearCollision(Puzzle p, int i, int j) {
        // ei toimi vielä täysin järkevästi: jos laatat ovat samalla rivillä,
        // mutta se ei ole oikea rivi jommalle kummalle laatoista, ei törmäystä
        // käytännössä pitäisi tapahtua. Nyt laskee senkin törmäykseksi.
        
        if (p.state()[j] < p.state()[i]) {
            return 2;
        }
        return 0;
    }
    
    /**
     * Method for counting sum of Manhattan distances between tiles of given puzzle and
     * linear collision.
     * 
     * @param p Puzzle which heuritics is being calculated
     * @return Heuristic value of the puzzle
     */
    public int heuristic(Puzzle p) {
        int value = 0;
        
        for (int i = 0; i < p.state().length; i++) {
            if (p.state()[i] != 0) {
                value += countManhattanDistance(i, p.state()[i] - 1);
            }
            
            for (int j = i; j < p.state().length; j++) {
                if (i % 4 == j % 4 || i / 4 == j / 4) {
                    value += countLinearCollision(p, i, j);
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
        return o1.moves() + heuristic(o1) - o2.moves() - heuristic(o2);
    }
}
