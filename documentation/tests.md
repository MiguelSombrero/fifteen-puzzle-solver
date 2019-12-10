# Test document

Applications correctness has been tested with automatic unit tests and manually via user interface. Performance tests are performed with solving multiple 15-puzzles and calculating average solving time for each algorithm.

## Test coverage

Overall test coverage as of 10.12.2019 is ~ 95-98%:

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/overall_coverage.png)

## Unit testing

### fifteenpuzzlesolver, fifteenpuzzlesolver.ui

These packages contains main method and user interface and has left outside of the unit tests.

### fifteenpuzzlesolver.domain

This package contains classes for algorithms and puzzles. Test coverage is ~ 93-97%, although I'm not happy with the tests of AStar and IDAStar classes: I found it hard to test correctness of search() method. There could be also more tests about finding optimal solution.

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/domain_coverage.png)

### fifteenpuzzlesolver.service

Package contains only PuzzleService class, which provides methods for generating and solving puzzles. Test coverage is ~ 83-99%. There is basically one branch I'm not testing, which decreases test coverage. I'm using mock classes for mocking PuzzleSolvers, so that I can tests benchmark methods correctly.

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/service_coverage.png)

### fifteenpuzzlesolver.utils

Package contains data structures and puzzle generator. Test coverage is 100%. With PuzzleGenerator class I mainly tested that the returned puzzles are 'valid' puzzles.

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/utils_coverage.png)

## Manual testing

Manual tests are run mostly to check that user interface works correctly. Also manual testing has been run for single puzzles and for PuzzleGenerator, in order check their correctness.

## Performance testing

Explanation of table column names:

- Hardness = Number of moves used to generate puzzle. Puzzles are created with generatePuzzleByMoves() method, which traverses backwards from solved state by given moves
- n = Number of puzzles solved when calculating averages
- e = Application crashed due to a OutOfMemory error
- "-" = Out of patience error (solving took too long to wait)
- Time = Average time for solving puzzle
- Moves = Average of used moves for solving puzzle
- A* position = A* algorithm with position based heuristics
- A* manhattan = A* algorithm with manhattan heuristics
- A* collision = A* algorithm with manhattan + linear collision heuristics
- IDA* = IDA* algorithm with mentioned heuristic

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
120 (n=30) | e | e | e | - | 30.833s | 6.448s
140 (n=30) | e | e | e | - | 89.947s | 26.715s
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
120 (n=30) | e | e | e | - | 41 | 41
140 (n=30) | e | e | e | - | 43 | 43
160 (n=30) | e | e | 43 | - | 42 | 43

### Analyzis of heuristics

As performance tests clearly shows, position based heuristic is by far the worst. It performs as well as the other heuristics with easy puzzles (under 30 moves), but cripples completely with puzzles harder than that. I guess the number of tiles in wrong position cannot assort different game states well enough - there is simply too much draws.

Manhattan heuristic is clear improvement to position based heuristic. It performs as well as liner collision heuristic - some cases even better - with semi-hard (under 70 moves) puzzles. When it goes harder puzzles than that, linear collision is starting to get a head.

It seems - according to the tests - that linear collision heuristics might overestimate the cost of reaching the solved state; in some cases, path found with linear collision heuristic has more moves than other heuristics. This might also explain why manhattan heuristic performs some cases better than linear collision - it needs to traverse shorter path to find goal. I have tested with 100K random puzzles, that heuristics never estimates the cost over 80 moves, but this doesn't prove that found solution is optimal.

### Analyzis of algorithms

A* is almost as fast as IDA* algorithm with easy puzzles. Harder the puzzle, greater the difference between A* and IDA* increases. A* algorithms clear downside is its memory consumption; solving hard puzzles usually leads to OutOfMemory error, because Java's heap space runs out. A* alforithm saves all the game states it is about to visit in priority queue, even though some of the states are clearly ones that it never traverses.

As stated in the implementation document, time-complexity of A* is O(lg(V)*V) and IDA* is O(V). Difference comes from the extraction of nodes from min-heap, which takes approximately O(lg(V)) time. A* algorithm seems to be little slower than IDA* algorithm, which verifies in practise the difference between time-complexities. However, logarithm stays very small even with huge V, so difference between effectiviness of A* and IDA* might also be due to something else, memory consumption maybe.

