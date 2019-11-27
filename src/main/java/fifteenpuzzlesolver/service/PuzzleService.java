
package fifteenpuzzlesolver.service;

import fifteenpuzzlesolver.domain.Puzzle;
import fifteenpuzzlesolver.domain.PuzzleSolver;
import fifteenpuzzlesolver.utils.ArrayList;
import fifteenpuzzlesolver.utils.PuzzleGenerator;

/**
 * Class which provides services for solving and generating puzzles.
 * @author miika
 */
public class PuzzleService {
 
    private PuzzleSolver astarPosition;
    private PuzzleSolver astarManhattan;
    private PuzzleSolver astarLinear;
    private PuzzleSolver idaStarPosition;
    private PuzzleSolver idaStarManhattan;
    private PuzzleSolver idaStarLinear;
    
    private PuzzleGenerator generator;
    
    /**
     * Constructor class. Initializes all the algorithms which can be used to solve puzzles.
     * 
     * @param astarPosition A* -algorithm with position based heuristic
     * @param astarManhattan A* -algorithm with manhattan heuristic
     * @param astarLinear A* -algorithm with manhattan + linear collision heuristic
     * @param idaStarPosition IDA* -algorithm with position based heuristic
     * @param idaStarManhattan IDA* -algorithm with manhattan heuristic
     * @param idaStarLinear IDA* -algorithm with manhattan + linear collision heuristic
     * @param generator Class for generating puzzles
     */
    public PuzzleService(PuzzleGenerator generator,
            PuzzleSolver astarPosition, PuzzleSolver astarManhattan, PuzzleSolver astarLinear,
            PuzzleSolver idaStarPosition, PuzzleSolver idaStarManhattan, PuzzleSolver idaStarLinear) {
        
        this.astarPosition = astarPosition;
        this.astarManhattan = astarManhattan;
        this.astarLinear = astarLinear;
        this.idaStarPosition = idaStarPosition;
        this.idaStarManhattan = idaStarManhattan;
        this.idaStarLinear = idaStarLinear;
        this.generator = generator;
    }
    
    /**
     * Method which solves puzzle using A* and position based heuristics.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if puzzle is solvable. Null otherwise
     */
    public Puzzle solveWithAPosition(Puzzle puzzle) {
        return this.astarPosition.solve(puzzle);
    }
    
    /**
     * Method which solves puzzle using A* and manhattan heuristics.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if puzzle is solvable. Null otherwise
     */
    public Puzzle solveWithAManhattan(Puzzle puzzle) {
        return this.astarManhattan.solve(puzzle);
    }
    
    /**
     * Method which solves puzzle using A* and linear collision heuristics.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if puzzle is solvable. Null otherwise
     */
    public Puzzle solveWithALinearCollision(Puzzle puzzle) {
        return this.astarLinear.solve(puzzle);
    }
    
    /**
     * Method which solves puzzle using IDA* and position based heuristics.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if puzzle is solvable. Null otherwise
     */
    public Puzzle solveWithIDAPosition(Puzzle puzzle) {
        return this.idaStarPosition.solve(puzzle);
    }
    
    /**
     * Method which solves puzzle using IDA* and manhattan heuristics.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if puzzle is solvable. Null otherwise
     */
    public Puzzle solveWithIDAManhattan(Puzzle puzzle) {
        return this.idaStarManhattan.solve(puzzle);
    }
    
    /**
     * Method which solves puzzle using IDA* and linear collision heuristics.
     * 
     * @param puzzle Puzzle to solve
     * @return Solved puzzle if puzzle is solvable. Null otherwise
     */
    public Puzzle solveWithIDALinearCollision(Puzzle puzzle) {
        return this.idaStarLinear.solve(puzzle);
    }
    
    /**
     * Solves n puzzles using given puzzle solver and heuristics. Used for calculating average
     * solving time for specific puzzle with specific algorithm.
     * 
     * @param puzzles Puzzles to solve for bencmarking
     * @param solver Algorithm for solving puzzle
     * @return data consisting average solving time in milliseconds and average moves
     */
    public long[] benchmark(ArrayList<Puzzle> puzzles, PuzzleSolver solver) {
        long data[] = new long[2];
        
        for (int i = 0; i < puzzles.size(); i++) {
            long time1 = System.currentTimeMillis();
            Puzzle solved = solver.solve(puzzles.get(i));
            long time2 = System.currentTimeMillis();
            data[0] += time2 - time1;
            data[1] += solved.getMoves();
        }
        
        data[0] = data[0] / puzzles.size();
        data[1] = data[1] / puzzles.size();
        
        return data;
    }
    
    /**
     * Benchmarks puzzle n times using A* position algorithm.
     * 
     * @param puzzles puzzles Puzzles to solve for Benchmarking
     * @return data consisting average solving time in milliseconds and average moves
     */
    public long[] benchmarkAStarPosition(ArrayList<Puzzle> puzzles) {
        return benchmark(puzzles, this.astarPosition);
    }
    
    /**
     * Benchmarks puzzle n times using A* manhattan algorithm.
     * 
     * @param puzzles Puzzles to solve for Benchmarking
     * @return data consisting average solving time in milliseconds and average moves
     */
    public long[] benchmarkAStarManhattan(ArrayList<Puzzle> puzzles) {
        return benchmark(puzzles, this.astarManhattan);
    }
    
    /**
     * Benchmarks puzzle n times using A* linear collision algorithm.
     * 
     * @param puzzles Puzzles to solve for Benchmarking
     * @return data consitig average solving time in milliseconds and average moves
     */
    public long[] benchmarkAStarLinearCollision(ArrayList<Puzzle> puzzles) {
        return benchmark(puzzles, this.astarLinear);
    }
    
    
    /**
     * Benchmarks puzzle n times using IDA* position algorithm.
     * 
     * @param puzzles puzzles Puzzles to solve for Benchmarking
     * @return data consisting average solving time in milliseconds and average moves
     */
    public long[] benchmarkIDAStarPosition(ArrayList<Puzzle> puzzles) {
        return benchmark(puzzles, this.idaStarPosition);
    }
    
    /**
     * Benchmarks puzzle n times using IDA* manhattan algorithm.
     * 
     * @param puzzles Puzzles to solve for Benchmarking
     * @return data consisting average solving time in milliseconds and average moves
     */
    public long[] benchmarkIDAStarManhattan(ArrayList<Puzzle> puzzles) {
        return benchmark(puzzles, this.idaStarManhattan);
    }
    
    /**
     * Benchmarks puzzle n times using IDA* linear collision algorithm.
     * 
     * @param puzzles Puzzles to solve for Benchmarking
     * @return data consitig average solving time in milliseconds and average moves
     */
    public long[] benchmarkIDAStarLinearCollision(ArrayList<Puzzle> puzzles) {
        return benchmark(puzzles, this.idaStarLinear);
    }
    
    /**
     * Method for generating random 15-puzzle. Puzzle (usually) gets harder when shuffles increase
     * 
     * @param shuffles Number of shuffles from solved puzzle state
     * @return 15-puzzle
     */
    public Puzzle generateRandomPuzzle(int shuffles) {
        return this.generator.generateRandomPuzzle(shuffles);
    }
    
    /**
     * Method for generating 15-puzzle. Puzzle gets harder when moves increase
     * because it moves further away from solved state
     * 
     * @param moves Moves from solved state to random direction
     * @return 15-puzzle
     */
    public Puzzle generatePuzzleByMoves(int moves) {
        return this.generator.generatePuzzleByMoves(moves);
    }
    
    /**
     * Generates list of puzzles.
     * 
     * @param puzzles Number of puzzles to generate
     * @param moves Moves used for generating each puzzle
     * @return List of generated puzzles
     */
    public ArrayList<Puzzle> generateMultiplePuzzles(int puzzles, int moves) {
        ArrayList<Puzzle> p = new ArrayList<>();
        
        while (puzzles > 0) {
            Puzzle puzzle = generatePuzzleByMoves(moves);
            p.add(puzzle);
            puzzles--;
        }
        
        return p;
    }
}
