# Week 2

- [Hours](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/hours.md)

## Synopsis

- Programmed game logic and both heuristics (manhattan & position based)
- Unit testing for implemented classes
- JavaDoc for implemented classes
- First test coverage report

## Problems

It seems that A* is not efficient enough for solving hard 15-puzzles, at least not with heuristics used. Heuristics traverse too many game states when sampling the shortest path to the goal state. For now, I use a bit simpler 15-puzzles in tests, which both heuristics solve within 30 seconds.

## Questions

Need to investigate if there is way to increase performance of A* -algorithm. "Linear collision" heuristic looks promising. 

I have found articles and texts about IDA* (iterative deepening A*) -algorithm, which supposed to be efficient enough for solving 15-puzzles. If I have time, I could try to implement that algorithm as well.

## Next Week

- Implement at least one own data structure (ArrayList for example)
- Try to improve A* -algorithmic performance