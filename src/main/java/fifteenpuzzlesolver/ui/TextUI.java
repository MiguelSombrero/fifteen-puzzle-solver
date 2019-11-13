
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
        System.out.println("m - Solve puzzle with A* + manhattan");
        System.out.println("p - Solve puzzle with A* + position");
        System.out.println("l - Solve puzzle with A* + linear collision");
        System.out.println("bm - Benchmark A* + manhattan");
        System.out.println("bp - Benchmark A* + position");
        System.out.println("bl - Benchmark A* + linear collision");
        System.out.println("x - Exit");
    }
    
    /**
     * Prints moves and time taken for solve given puzzle.
     * 
     * @param time1 Starting time of solving puzzle
     * @param solved Solved puzzle
     */
    public void solve(long time1, Puzzle solved) {
        long time2 = System.currentTimeMillis();
        
        System.out.println("-----------------");
        System.out.println(solved.toString());
        System.out.println("Moves taken: " + solved.moves());
        System.out.println("Time taken: " + (time2 - time1) / 1000 + " seconds");
        System.out.println("-----------------");
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
                System.out.println("-----------------");
                System.out.println("GENERATED PUZZLE:");
                System.out.println(puzzle.toString());
                
            } else if (c.equals("m") && puzzle != null) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithManhattan(puzzle));
                
            } else if (c.equals("p") && puzzle != null) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithPosition(puzzle));
                
            } else if (c.equals("l") && puzzle != null) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithLinearCollision(puzzle));
                
            } else if (c.equals("bm") && puzzle != null) {
                System.out.println("How many iterations?");
                int i = Integer.valueOf(this.reader.nextLine());
                long time = this.service.benchmarkAStarManhattan(puzzle, i);
                System.out.println("Average solving time: " + time / 1000 + " seconds");
                
            } else if (c.equals("bp") && puzzle != null) {
                System.out.println("How many iterations?");
                int i = Integer.valueOf(this.reader.nextLine());
                long time = this.service.benchmarkAStarPosition(puzzle, i);
                System.out.println("Average solving time: " + time / 1000 + " seconds");
                
            } else if (c.equals("bl") && puzzle != null) {
                System.out.println("How many iterations?");
                int i = Integer.valueOf(this.reader.nextLine());
                long time = this.service.benchmarkAStarLinearCollision(puzzle, i);
                System.out.println("Average solving time: " + time / 1000 + " seconds");
                
            } else if (c.equals("x")) {
                break;
                
            } else {
                System.out.println("Wrong command!");
            }
        }
    }
}
