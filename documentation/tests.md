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

- Generate set of 20 random puzzles (with generatePuzzleByMoves method) with n moves
- Solve each 20 puzzles set with all the heuristics
- Calcutate average solving time and average moves for each set and each heuristic

Explanation of table column names:

- Hardness = Number of moves used to generate puzzle
- Time = Average time for solving 10 puzzles
- Moves = Average of used moves for solving 10 puzzles

### Performance of A* with position based heuristics

Hardness | Time | Moves
----|-------|------------
5 | 0.000s | 8
10 | 0.000s | 17
20 | 0.014s | 37
25 | 0.490 | 46
30 | 8.466 | 54
35 | - | "out of memory" 

### Performance of A* with manhattan heuristics

Hardness | Time | Moves
----|-------|------------
5 | 0.000s | 8
10 | 0.000s | 17
20 | 0.002s | 37
25 | 0.012s | 47
30 | 0.102s | 54
35 | 0.266s | 62
40 | 0.179s | 69
45 | 0.201s | 75
50 | 3.939s | 82
55 | 3.498s | 90
60 | 1.345s | 93
65 | 4.169s | 101
70 | 3.373s | 105 
75 | - | "out of memory"

### Performance of A* with manhattan + linear collision heuristics

Hardness | Time | Moves
----|-------|------------
5 | 0.000s | 8
10 | 0.001s | 17
20 | 0.003s | 37
25 | 0.024s | 47
30 | 0.162s | 56
35 | 0.217s | 62
40 | 0.546s | 70
45 | 0.155s | 75
50 | 1.231s | 82
55 | 3.554s | 91
60 | 1.234s | 93
65 | 2.348s | 101
70 | 2.755s | 105
75 | 5.758s | 113
80 | 3.343s | 116
100 | 16.074s | 140
120 | 25.628s | 162
140 | 11.370s | 182
180 | - | "out of memory"