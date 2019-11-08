
package fifteenpuzzlesolver.ui;

import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.service.PuzzleService;
import java.util.Scanner;

/**
 * Text User Interface for the application.
 * @author miika
 */
public class TextUI {
    
    private Scanner reader;
    private PuzzleService service;
    
    /**
     * Constructor method.
     * @param reader Scanner which reads user input
     * @param service Class that provides methods for solving puzzles
     */
    public TextUI(Scanner reader, PuzzleService service) {
        this.reader = reader;
        this.service = service;
    }
    
    /**
     * Method for printing game menu.
     */
    public void printOptions() {
        System.out.println("OPTIONS");
        System.out.println("g - Generate puzzle");
        System.out.println("m - Solve puzzle with manhattan heuristics");
        System.out.println("p - Solve puzzle with position heuristics");
        System.out.println("x - Exit");
    }
    
    public void solve(long time1, Puzzle solved) {
        long time2 = System.currentTimeMillis();
                
        System.out.println(solved.toString());
        System.out.println("Moves taken: " + solved.moves());
        System.out.println("Time taken: " + (time2 - time1) / 1000 + " seconds");
    }
    
    /**
     * Initializes game and starts to listen user inputs.
     */
    public void initialize() {
        Puzzle puzzle = null;
            
        while (true) {
            printOptions();
            String c = this.reader.nextLine();
            
            if (c.equals("g")) {
                System.out.println("How many shuffles");
                int s = Integer.valueOf(this.reader.nextLine());
                puzzle = this.service.generatePuzzle(s);
                System.out.println("GENERATED PUZZLE:");
                System.out.println(puzzle.toString());
                
            } else if (c.equals("m") && puzzle != null) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithManhattan(puzzle));
                
            } else if (c.equals("p") && puzzle != null) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithPosition(puzzle));
                
            } else if (c.equals("x")) {
                break;
                
            } else {
                System.out.println("Wrong command!");
            }
        }
    }
}
