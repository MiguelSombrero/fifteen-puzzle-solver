# Test document

Applications correctness has been tested with automatic unit tests and manually via user interface. Performance tests are performed with solving multiple 15-puzzles and calculating average solving time for each algorithm.

## Test coverage

Overall test coverage as of 22.11.2019:

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/overall_coverage.png)

## Unit testing

### fifteenpuzzlesolver, fifteenpuzzlesolver.ui

These packages contains main method and user interface and has left outside of the unit tests.

### fifteenpuzzlesolver.domain

This package contains classes for algorithms and puzzles. Test coverage is ~ 89%, although I'm not happy with the tests of AStar class: I found it hard to test correctness of search method. Also IDAStar class is missing tests.

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/domain_coverage.png)

### fifteenpuzzlesolver.service

Package contains only PuzzleService class, which provides methods for generating and solving puzzles. Test coverage is ~ 30%. I haven't found good way to test benchmarking methods, which why test coverage remains that low.

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/service_coverage.png)

### fifteenpuzzlesolver.utils

Package contains data structures and puzzle generator. Test coverage is 100%. With PuzzleGenerator class I mainly tested that the returned puzzles are 'valid' puzzles.

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/utils_coverage.png)

## Manual testing


## Performance testing

All performance tests are performed with the following idea:

- Generate set of 20 random puzzles (with generatePuzzleByMoves method) with n moves (n is rising 5, 10, 20, ...)
- Solve each 20 puzzles set with all the heuristics and calcutate average solving time and average moves for each set and each heuristic

**Notice: after puzzles with 80 moves, benchmarking is done with 10 puzzles sets to save some time**

Explanation of table column names:

- Hardness = Number of moves used to generate puzzle
- Time = Average time for solving puzzle
- Moves = Average of used moves for solving puzzle
- A* position = A* algorithm with position based heuristics
- A* manhattan = A* algorithm with manhattan heuristics
- A* collision = A* algorithm with manhattan + linear collision heuristics
- IDA* collision = IDA* algorithm with manhattan + linear collision heuristics

### Time

Hardness | A* position | A* manhattan | A* collision | IDA* collision
---------|-------------|--------------|--------------|---------------
5  | 0.000s | 0.000s | 0.000s | 0.000s
10 | 0.000s | 0.000s | 0.000s | 0.001s
20 | 0.011s | 0.003s | 0.014s | 0.517s
25 | 0.322s | 0.006s | 0.021s | 0.390s
30 | 4.404s | 0.037s | 0.059s | 1.107s
35 | -      | 0.214s | 0.321s | 2.446s
40 | -      | 0.286s | 0.442s | 4.423s
50 | -      | 2.644s | 1.723s | 23.513s
60 | -      | 1.483s | 1.313s | 19.659s
70 | -      | -      | 3.193s | 65.332s
80 | -      | -      | 3.391s | 275.171s
90 | -      | -      | 20.918s | -
100 | -     | -      | 9.265s | -
130 | -     | -      | 48.413s | -

### Moves

Hardness | A* position | A* manhattan | A* collision | IDA* collision
---------|-------------|--------------|--------------|---------------
5  | 4  | 4  | 4  | 4
10 | 9  | 9  | 9  | 11
20 | 17 | 19 | 19 | 22
25 | 21 | 23 | 23 | 25
30 | 24 | 26 | 27 | 27
35 | -  | 29 | 30 | 31
40 | -  | 31 | 31 | 32
50 | -  | 34 | 35 | 36
60 | -  | 37 | 37 | 38
70 | -  | -  | 36 | 37
80 | -  | -  | 35 | 36
90 | -  | -  | 42 | -
100 | - | -  | 41 | -
130 | - | -  | 43 | -