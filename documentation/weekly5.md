# Week 5

- [Hours](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/hours.md)

## Synopsis

- Peer review #1
- Refactoring
- Updated documention
- Optimization for A* and IDA* (pruning)

## Problems & Questions

I'm having hard time for optimizing A* and IDA* algorithms. Only optimization that I've come up with, that (maybe) has improved performance for both algorithms, is to limit search three by moves. Since it is proved in this [article](http://www.iro.umontreal.ca/~gendron/Pisa/References/BB/Brungger99.pdf), that an optimal solution for 15-puzzle can always be found within 80 moves, search tree can be bounded to 80 moves maximum.

A* algorithm has memory usage problem: algorithm traverses too many game states when searching solution.

## Next Week

- Peer review #2
- Documentation
- More optimization for A* and IDA*
- Increasing test coverage