
package fifteenpuzzlesolver.ui;

import fifteenpuzzlesolver.astar.AStar;
import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.astar.StateComparatorManhattan;
import fifteenpuzzlesolver.astar.StateComparatorPosition;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Text User Interface for the application.
 * @author miika
 */
public class TextUI {
    
    private Scanner reader;
    private AStar astar;
    private PuzzleGenerator generator;
    
    /**
     * Constructor method.
     * @param reader Scanner which reads user input
     * @param astar AStar algorithm class for solving puzzles
     * @param generator Utility class for generating solvable puzzles
     */
    public TextUI(Scanner reader, AStar astar, PuzzleGenerator generator) {
        this.reader = reader;
        this.astar = astar;
        this.generator = generator;
    }
    
    /**
     * Method for printing game menu.
     */
    public void printOptions() {
        System.out.println("OPTIONS");
        System.out.println("e - Generate easy puzzle");
        System.out.println("h - Generate hard puzzle");
        System.out.println("m - Solve puzzle with manhattan heuristics");
        System.out.println("p - Solve puzzle with position heuristics");
        System.out.println("x - Exit");
    }
    
    public void solve(Puzzle puzzle, Comparator heuristic) {
        long time1 = System.currentTimeMillis();
        Puzzle endState = astar.traverse(puzzle, heuristic);
        long time2 = System.currentTimeMillis();
        
        System.out.println(endState.toString());
        System.out.println("Moves taken: " + endState.moves());
        System.out.println("Time taken: " + (time2 - time1) / 1000 + " seconds");
    }
    
    /**
     * Initializes game and starts to listen user inputs.
     */
    public void initialize() {
        Puzzle puzzle = null;
            
        while (true) {
            printOptions();
            String c = reader.nextLine();
            
            if (c.equals("e")) {
                puzzle = generator.generateEasyPuzzle();
                System.out.println("GENERATED PUZZLE:");
                System.out.println(puzzle.toString());
                
            } else if (c.equals("h")) {
                puzzle = generator.generateHardPuzzle();
                System.out.println("GENERATED PUZZLE:");
                System.out.println(puzzle.toString());
                
            } else if (c.equals("m")) {
                if (puzzle == null) {
                    System.out.println("No puzzle to solve - try to generate one!");
                    continue;
                }
                StateComparatorManhattan manhattan = new StateComparatorManhattan();
                solve(puzzle, manhattan);
                
            } else if (c.equals("p")) {
                if (puzzle == null) {
                    System.out.println("No puzzle to solve - try to generate one!");
                    continue;
                }
                StateComparatorPosition position = new StateComparatorPosition();
                solve(puzzle, position);
                
            } else if (c.equals("x")) {
                break;
                
            } else {
                System.out.println("Wrong command!");
            }
        }
    }
}
