# Test document

Applications correctness has been tested with automatic unit tests and manually via user interface. Performance tests are performed with solving multiple 15-puzzles and calculating average solving time for each algorithm.

## Test coverage

Overall test coverage as of 29.11.2019:

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/overall_coverage.png)

## Unit testing

### fifteenpuzzlesolver, fifteenpuzzlesolver.ui

These packages contains main method and user interface and has left outside of the unit tests.

### fifteenpuzzlesolver.domain

This package contains classes for algorithms and puzzles. Test coverage is ~ 95%, although I'm not happy with the tests of AStar and IDAStar classes: I found it hard to test correctness of search() method. There could be also more tests about finding optimal solution.

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/domain_coverage.png)

### fifteenpuzzlesolver.service

Package contains only PuzzleService class, which provides methods for generating and solving puzzles. Test coverage is ~ 80-100%. I'm using mock classes for mocking PuzzleSolvers, so that I can tests benchmark methods correctly.

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/service_coverage.png)

### fifteenpuzzlesolver.utils

Package contains data structures and puzzle generator. Test coverage is near 100%. With PuzzleGenerator class I mainly tested that the returned puzzles are 'valid' puzzles.

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/utils_coverage.png)

## Manual testing

Manual tests are run mostly to check that user interface works correctly. Also manual testing has been run for single puzzles and for PuzzleGenerator, in order check their correctness.

## Performance testing

All performance tests are performed with the following idea:

- Generate set of n random puzzles (with generatePuzzleByMoves method) with x moves (x is rising 5, 10, 20, ...)
- Solve each n puzzles set with all the heuristics and calcutate average solving time and average moves for each set and each heuristic

Explanation of table column names:

- Hardness = Number of moves used to generate puzzle
- n = Number of puzzles solved when calculating averages
- e = Application crashed due to a OutOfMemory error
- "-" = Out of patience (solving took too long to wait)
- Time = Average time for solving puzzle
- Moves = Average of used moves for solving puzzle
- A* position = A* algorithm with position based heuristics
- A* manhattan = A* algorithm with manhattan heuristics
- A* collision = A* algorithm with manhattan + linear collision heuristics
- IDA* collision = IDA* algorithm with manhattan + linear collision heuristics

### Time

Hardness | A* position | A* manhattan | A* collision | IDA* position | IDA* manhattan | IDA* collision
---------|-------------|--------------|--------------|---------------|----------------|---------------
5 (n=50) | 0.000s | 0.000s | 0.000s | 0.000s | 0.000s | 0.000s
10 (n=50) | 0.000s | 0.000s | 0.000s | 0.000s | 0.000s | 0.000s
20 (n=50) | 0.011s | 0.002s | 0.009s | 0.006s | 0.001s | 0.006s
30 (n=50) | e | 0.043s | 0.068s | 3.847s | 0.028s | 0.032s
40 (n=50) | e | 0.305s | 0.360s | - | 0.191s | 0.411s
50 (n=40) | e | 2.474s | 5.010s | - | 1.987s | 1.540s
60 (n=40) | e | 3.522s | 3.877s | - | 2.390s | 1.370s
70 (n=40) | e | e | 4.303s | - | 3.576s | 2.187s
80 (n=40) | e | e | 7.714s | - | 6.768s | 3.595s
90 (n=30) | e | e | 8.283s | - | 10.403s | 3.355s
100 (n=30) | e | e | 14.361s | - | 18.744s | 5.478s
120 (n=30) | e | e | 8.963s | - | 10.933s | 3.493s
140 (n=20) | e | e | 17.103s | - | 10.199s | 4.890s
160 (n=30) | e | e | 42.664s | - | 33.020s | 16.681s

### Moves

Hardness | A* position | A* manhattan | A* collision | IDA* position | IDA* manhattan | IDA* collision
---------|-------------|--------------|--------------|---------------|----------------|---------------
5 (n=50) | 4  | 4 | 4 | 4 | 4 | 4
10 (n=50) | 8 | 8 | 8 | 8 | 8 | 8
20 (n=50) | 18 | 18 | 19 | 18 | 18 | 19
30 (n=50) | e | 25 | 25 | 24 | 24 | 25
40 (n=50) | e | 29 | 30 | - | 29 | 29
50 (n=40) | e | 33 | 33 | - | 33 | 33
60 (n=40) | e | 35 | 36 | - | 35 | 35
70 (n=40) | e | e | 38 | - | 37 | 37
80 (n=40) | e | e | 38 | - | 38 | 38
90 (n=30) | e | e | 39 | - | 38 | 38
100 (n=30) | e | e | 39 | - | 39 | 38
120 (n=30) | e | e | 41 | - | 41 | 41
140 (n=20) | e | e | 41 | - | 40 | 40
160 (n=30) | e | e | 43 | - | 42 | 43