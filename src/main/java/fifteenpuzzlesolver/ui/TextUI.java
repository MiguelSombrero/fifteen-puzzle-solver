
package fifteenpuzzlesolver.ui;

import fifteenpuzzlesolver.astar.AStar;
import fifteenpuzzlesolver.domain.FifteenPuzzle;
import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.astar.StateComparatorManhattan;
import fifteenpuzzlesolver.astar.StateComparatorPosition;
import fifteenpuzzlesolver.utils.PuzzleGenerator;
import java.util.Comparator;
import java.util.Scanner;

public class TextUI {
    
    private Scanner reader;
    private AStar astar;
    private PuzzleGenerator generator;
    
    public TextUI(Scanner reader, AStar astar, PuzzleGenerator generator) {
        this.reader = reader;
        this.astar = astar;
        this.generator = generator;
    }
    
    public void printOptions() {
        System.out.println("OPTIONS");
        System.out.println("g - Generate puzzle");
        System.out.println("m - Solve puzzle with manhattan heuristics");
        System.out.println("p - Solve puzzle with position heuristics");
        System.out.println("x - Exit");
    }
    
    public void printPuzzle(Puzzle puzzle) {
        for (int i = 0; i < puzzle.state().length; i++) {
            System.out.print(puzzle.state()[i] + "\t");
            
            if ((i + 1) % 4 == 0) {
                System.out.println("");
            }
        }
        System.out.println("");
    }
    
    public void solve(Puzzle puzzle, Comparator heuristic) {
        long time1 = System.currentTimeMillis();
        Puzzle endState = astar.traverse(puzzle, heuristic);
        long time2 = System.currentTimeMillis();
        
        printPuzzle(endState);
        System.out.println(endState.toString());
        System.out.println("Time taken: " + (time2 - time1) / 1000 + " seconds");
    }
    
    public void initialize() {
        Puzzle puzzle = null;
            
        while (true) {
            printOptions();
            String c = reader.nextLine();
            
            if (c.equals("g")) {
                puzzle = generator.generatePuzzle();
                printPuzzle(puzzle);
            } else if (c.equals("m")) {
                if (puzzle == null) {
                    System.out.println("No puzzle to solve - try to generate one!");
                }
                StateComparatorManhattan manhattan = new StateComparatorManhattan();
                solve(puzzle, manhattan);
                
            } else if (c.equals("p")) {
                if (puzzle == null) {
                    System.out.println("No puzzle to solve - try to generate one!");
                }
                StateComparatorPosition position = new StateComparatorPosition();
                solve(puzzle, position);
                
            } else if (c.equals("x")) {
                break;
                
            } else {
                System.out.println("Wrong command!");
            }
        }
        
        
        
        // should be solvable
        int[] b1 = {
            15, 1, 2, 12,
            8, 5, 6, 11,
            4, 9, 10, 7,
            3, 14, 13, 0
        };
        
        // should be solvable
        int[] b2 = {
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            14, 15, 13, 0
        };
        
        // should be solvable
        int[] b6 = {
            1, 2, 3, 4,
            5, 6, 0, 8,
            9, 10, 11, 12,
            13, 7, 14, 15
        };
        
        //Puzzle puzzle = new FifteenPuzzle(b1);
        //Puzzle puzzle = new FifteenPuzzle(b2);
        //Puzzle puzzle = new FifteenPuzzle(b6, 0, 6);
        
    }
    
}
