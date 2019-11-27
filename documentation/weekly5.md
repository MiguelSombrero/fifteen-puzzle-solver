# Week 4

- [Hours](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/hours.md)

## Synopsis

- Peer review #1
- Refactoring
- Updated documention
- Optimization for A* and IDA* (pruning)

## Problems


## Questions & comments

I'm having hard time for optimizing A* and IDA* algorithms. Only major optimization that I've come up with, that has improved performance for both algorithms, is to limit search three by moves. Since it is proved in this [article](http://www.iro.umontreal.ca/~gendron/Pisa/References/BB/Brungger99.pdf), that an optimal solution for 15-puzzle can always be found within 80 moves, search tree can be bounded to 80 moves maximum.

I'm not happy about this kind of optimizations, since they are context relational; it works exactly with the given problem (15-puzzles), but are not generalizable for n-puzzles. In this point though, I'm happy with every possible optimization that works :)

## Next Week

- Peer review #2
- More optimization for A* and IDA*
- Increasing test coverage