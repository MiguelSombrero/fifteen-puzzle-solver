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

UNDER CONSTRUCTION

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
---------|-------------|--------------|--------------|---------------
5 (n=50) | 0.000s | 0.000s | 0.000s | 0.000s | 0.000s | 0.000s
10 (n=50) | 0.000s | 0.000s | 0.000s | 0.000s | 0.00s | 0.000s
20 (n=50) | 0.011s | 0.002s | 0.007s | 0.068s | 0.036s | 0.048s
30 (n=20) | e | 0.121s | 0.132s | - | 0.825s | 1.107s
40 (n=20) | e | 0.181s | 0.260s | - | 7.650s | 4.054s
50 (n=20) | e | 0.727s | 1.347s | - | - | 34.709s
60 (n=20) | e | e | 3.218s | - | - | -
70 (n=20) | e | e | 2.445s | - | - | -
80 (n=20) | e | e | 2.890s | - | - | -
90 (n=30) | e | e | 18.880s | - | - | -
100 (n=20) | e | e | 18.985s | - | - | -
120 (n=20) | e | e | e | - | - | -

### Moves

Hardness | A* position | A* manhattan | A* collision | IDA* position | IDA* manhattan | IDA* collision
---------|-------------|--------------|--------------|---------------
5 (n=50) | 4  | 4  | 4  | 4 | 4 | 4
10 (n=50) | 9  | 9  | 9  | 9 | 10 | 11
20 (n=50) | 18 | 19 | 19 | 18 | 21 | 22
30 (n=20) | e | 25 | 24 | - | 26 | 27
40 (n=20) | e | 30 | 30 | - | 31 | 31
50 (n=20) | e | 32 | 33 | - | - | 34
60 (n=20) | e | e | 35 | - | - | -
70 (n=20) | e | e | 38 | - | - | -
80 (n=20) | e | e | 38 | - | - | -
90 (n=30) | e | e | 40 | - | - | -
100 (n=20) | e | e | 40 | - | - | -
120 (n=20) | e | e | e | - | - | -