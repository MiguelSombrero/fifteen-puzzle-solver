# Week 3

- [Hours](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/hours.md)

## Synopsis

- Implemented HashSet data structure
- Trying to improve performance of A* -algorithm with minor changes
- Implemented new heuristic Linear collision for A*
- Tests and JavaDoc for Linear collision comparator class & HashSet

## Problems

When trying to solve harder 15-puzzles with manhattan or position based heuristics, I'm getting "OutOfMemoryError: Java heap space" when generating new game states. So it seems that A* -algorithm (with manhattan and position based heuristics) uses too much space for solving harder 15-puzzles. Still need to investigate possibility to use IDA* -algorithm for harder puzzles.

## Questions & comments

First benchmarkings indicate that Linear collision heuristic is much more efficient (in running time) than basic manhattan algorithm. No "OutOfMemoryError" at least not with "semi-hard" puzzles. Linear collision algorithm doesn't seem to find shortest path though; found paths are usually longer than with manhattan, so heuristics overestimates the lenght to the solution. 

In Linear collision heuristic I'm counting manhattan distance and in addition "linear collision" between tiles that are on the same row or column: if smaller tile is before greater tile, add 2 to the value.

## Next Week

- Implement PriorityQueue data structure
- Try to improve A* -algorithmic performance
- First performance tests maybe