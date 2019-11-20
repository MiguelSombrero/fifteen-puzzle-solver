
package fifteenpuzzlesolver.ui;

import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.service.PuzzleService;
import fifteenpuzzlesolver.utils.ArrayList;
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
        System.out.println("0 - Generate multiple puzzles");
        System.out.println("1 - Generate random puzzle");
        System.out.println("2 - Generate puzzle by moves");
        System.out.println("3 - Solve puzzle with A* + manhattan");
        System.out.println("4 - Solve puzzle with A* + position");
        System.out.println("5 - Solve puzzle with A* + linear collision");
        System.out.println("6 - Solve puzzle with IDA* + linear collision");
        System.out.println("7 - Benchmark A* + manhattan");
        System.out.println("8 - Benchmark A* + position");
        System.out.println("9 - Benchmark A* + linear collision");
        System.out.println("x - Exit");
    }
    
    /**
     * Prints average solving time and moves for benchmark.
     * 
     * @param data Array consisting average solving time and moves
     */
    public void printBenchmark(long[] data) {
        System.out.println("---------------------");
        System.out.println("Average solving time: " + data[0] / 1000.0 + " seconds");
        System.out.println("Average moves: " + data[1]);
        System.out.println("---------------------");
    }
    
    /**
     * Prints moves and time taken for solve given puzzle.
     * 
     * @param time1 Starting time of solving puzzle
     * @param solved Solved puzzle
     */
    public void solve(long time1, Puzzle solved) {
        if (solved == null) {
            System.out.println("Puzzle is unsolvable!");
            return;
        }
        long time2 = System.currentTimeMillis();
        
        System.out.println("-----------------");
        System.out.println(solved.toString());
        System.out.println("Moves taken: " + solved.getMoves());
        System.out.println("Time taken: " + (time2 - time1) / 1000 + " seconds");
        System.out.println("-----------------");
    }
    
    /**
     * Initializes game and starts to listen user inputs.
     */
    public void initialize() {
        Puzzle puzzle = null;
        ArrayList<Puzzle> puzzles = null;
        
        while (true) {
            printOptions();
            String c = this.reader.nextLine();
            
            if (c.equals("0")) {
                System.out.println("How many puzzles?");
                int p = Integer.valueOf(this.reader.nextLine());
                System.out.println("How many moves per puzzle?");
                int m = Integer.valueOf(this.reader.nextLine());
                puzzles = this.service.generateMultiplePuzzles(p, m);
                System.out.println("-----------------");
                System.out.println("GENERATED " + p + " PUZZLES");
                System.out.println("-----------------");
                
            } else if (c.equals("1")) {
                System.out.println("How many shuffles");
                int s = Integer.valueOf(this.reader.nextLine());
                puzzle = this.service.generateRandomPuzzle(s);
                System.out.println("-----------------");
                System.out.println("GENERATED PUZZLE:");
                System.out.println(puzzle.toString());
                
            } else if (c.equals("2")) {
                System.out.println("How many moves");
                int m = Integer.valueOf(this.reader.nextLine());
                puzzle = this.service.generatePuzzleByMoves(m);
                System.out.println("-----------------");
                System.out.println("GENERATED PUZZLE:");
                System.out.println(puzzle.toString());
                
            } else if (c.equals("3") && puzzle != null) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithManhattan(puzzle));
                
            } else if (c.equals("4") && puzzle != null) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithPosition(puzzle));
                
            } else if (c.equals("5") && puzzle != null) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithLinearCollision(puzzle));
                
            } else if (c.equals("6") && puzzle != null) {
                System.out.println(this.service.solveWithIDAStarLinearCollision(puzzle));
                
            } else if (c.equals("7") && puzzles != null) {
                printBenchmark(this.service.benchmarkAStarManhattan(puzzles));
                
            } else if (c.equals("8") && puzzles != null) {
                printBenchmark(this.service.benchmarkAStarPosition(puzzles));
                
            } else if (c.equals("9") && puzzles != null) {
                printBenchmark(this.service.benchmarkAStarLinearCollision(puzzles));
                
            } else if (c.equals("x")) {
                break;
                
            } else {
                System.out.println("Wrong command!");
            }
        }
    }
}
