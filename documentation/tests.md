# Test document

## Test coverage

Test coverage as of 15.11.2019:

![Coverage](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/test_coverage.png)

## Unit testing

### fifteenpuzzlesolver, fifteenpuzzlesolver.ui

These packages contains main method and user interface and has left outside of the unit tests.

### fifteenpuzzlesolver.domain

This package contains classes for algorithms and puzzles. Test coverage is near 100%, although I'm not happy tests about AStar class: I found it hard to write sensible test for this. Maybe should have split traverse() method for smaller pieces.

### fifteenpuzzlesolver.service

Package contains only PuzzleService class, which provides methods for generating and solving puzzles. Test coverage is about XXX%. I didn't find good way to test benchmarking methods, which why test coverage remain that low.

### fifteenpuzzlesolver.utils

Package contains data structures and puzzle generator. Test coverage is 100%. With PuzzleGenerator class I mainly tested that te puzzles returned are solvable and 15-puzzles.

## Manual testing


## Performance testing

All performance tests are performed with the following idea:

- Generate set of 10 random puzzles (with generatePuzzleByMoves method) with 5, 10, 15, ... , n moves
- Solve each 10 puzzles set with all the heuristics
- Calcutate average solving time and average moves for each set

Hardness = Number of moves used to generate puzzle
Time = Average time for solving 10 puzzles
Moves = Average of used moves for solving 10 puzzles

### Performance of A* with position based heuristics

Hardness | Time | Moves
----|-------|------------
5 | 0s | 8
10 | 0s | 18
30 | - | "out of memory"

### Performance of A* with manhattan heuristics

Hardness | Time | Moves
----|-------|------------
5 | 0s | 8
10 | 0s | 18
30 | 0s | 57
40 | 0s | 69
50 | 1s | 82
60 |  | 

### Performance of A* with manhattan + linear collision heuristics

Hardness | Time | Moves
----|-------|------------
5 | 0s | 8
10 | 0s | 18
30 | 0s | 57
40 | 0s | 69
50 | 0s | 82
60 |  | 
