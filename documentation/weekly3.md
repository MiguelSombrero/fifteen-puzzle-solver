# Week 3

- [Hours](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/hours.md)

## Synopsis

- Implemented HashSet data structure
- Trying to improve performance of A* -algorithm with minor changes
- Implemented new heuristic Linear collision for A*

## Problems

When trying to solve harder 15-puzzles, I'm getting "OutOfMemoryError: Java heap space" when generating new game states. So it seems that A* -algorithm uses too much space for solving harder 15-puzzles. Still need to investigate possibility to use IDA* -algorithm for harder puzzles.

## Questions & comments

First benchmarkings indicate that Linear collision heuristic seems to be much more efficient than basic manhattan algorithm. No "OutOfMemoryError" at least not with "semi-hard" puzzles. In Linear collision heuristic I'm counting manhattan distance and in addition "linear collision" between tiles that are on the same row or column: if smaller tile is before greater tile, add 2 to the value.

## Next Week

- Implement PriorityQueue data structure
- Try to improve A* -algorithmic performance
- First performance tests maybe