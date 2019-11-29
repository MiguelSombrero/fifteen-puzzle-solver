
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
        System.out.println("p0 - Generate 80 moves puzzle");
        System.out.println("p1 - Generate multiple puzzles");
        System.out.println("p2 - Generate random puzzle");
        System.out.println("p3 - Generate puzzle by moves");
        System.out.println("s1 - Solve puzzle with A* + position");
        System.out.println("s2 - Solve puzzle with A* + manhattan");
        System.out.println("s3 - Solve puzzle with A* + linear collision");
        System.out.println("s4 - Solve puzzle with IDA* + position");
        System.out.println("s5 - Solve puzzle with IDA* + manhattan");
        System.out.println("s6 - Solve puzzle with IDA* + linear collision");
        System.out.println("b1 - Benchmark A* + position");
        System.out.println("b2 - Benchmark A* + manhattan");
        System.out.println("b3 - Benchmark A* + linear collision");
        System.out.println("b4 - Benchmark IDA* + position");
        System.out.println("b5 - Benchmark IDA* + manhattan");
        System.out.println("b6 - Benchmark IDA* + linear collision");
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
            
            if (puzzle == null && c.charAt(0) == 's') {
                System.out.println("Create puzzle first! [p2 or p3 command]");
                continue;
            }
            
            if (puzzles == null && c.charAt(0) == 'b') {
                System.out.println("Create multiple puzzles first! [p1 command]");
                continue;
            }
            
            if (c.equals("p0")) {
                puzzle = this.service.generate80movesPuzzle();
                System.out.println("-----------------");
                System.out.println("GENERATED PUZZLE:");
                System.out.println(puzzle.toString());
                System.out.println("-----------------");
                
            } else if (c.equals("p1")) {
                System.out.println("How many puzzles?");
                int p = Integer.valueOf(this.reader.nextLine());
                System.out.println("How many moves per puzzle?");
                int m = Integer.valueOf(this.reader.nextLine());
                puzzles = this.service.generateMultiplePuzzles(p, m);
                System.out.println("-----------------");
                System.out.println("GENERATED " + p + " PUZZLES");
                System.out.println("-----------------");
                
            } else if (c.equals("p2")) {
                System.out.println("How many shuffles");
                int s = Integer.valueOf(this.reader.nextLine());
                puzzle = this.service.generateRandomPuzzle(s);
                System.out.println("-----------------");
                System.out.println("GENERATED PUZZLE:");
                System.out.println(puzzle.toString());
                System.out.println("-----------------");
                
            } else if (c.equals("p3")) {
                System.out.println("How many moves");
                int m = Integer.valueOf(this.reader.nextLine());
                puzzle = this.service.generatePuzzleByMoves(m);
                System.out.println("-----------------");
                System.out.println("GENERATED PUZZLE:");
                System.out.println(puzzle.toString());
                System.out.println("-----------------");
                
            } else if (c.equals("s1")) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithAPosition(puzzle));
                
            } else if (c.equals("s2")) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithAManhattan(puzzle));
                
            } else if (c.equals("s3")) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithALinearCollision(puzzle));
                
            } else if (c.equals("s4")) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithIDAPosition(puzzle));
                
            } else if (c.equals("s5")) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithIDAManhattan(puzzle));
                
            } else if (c.equals("s6")) {
                long time1 = System.currentTimeMillis();
                solve(time1, this.service.solveWithIDALinearCollision(puzzle));
                
            } else if (c.equals("b1")) {
                printBenchmark(this.service.benchmarkAStarPosition(puzzles));
                
            } else if (c.equals("b2")) {
                printBenchmark(this.service.benchmarkAStarManhattan(puzzles));
                
            } else if (c.equals("b3")) {
                printBenchmark(this.service.benchmarkAStarLinearCollision(puzzles));
                
            } else if (c.equals("b4")) {
                printBenchmark(this.service.benchmarkIDAStarPosition(puzzles));
                
            } else if (c.equals("b5")) {
                printBenchmark(this.service.benchmarkIDAStarManhattan(puzzles));
                
            } else if (c.equals("b6")) {
                printBenchmark(this.service.benchmarkIDAStarLinearCollision(puzzles));
                
            } else if (c.equals("x")) {
                break;
                
            } else {
                System.out.println("Wrong command!");
            }
        }
    }
}
