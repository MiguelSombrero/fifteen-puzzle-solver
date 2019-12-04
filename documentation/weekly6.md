# Week 6

- [Hours](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/hours.md)

## Synopsis

- Implemented Stack data structure and tests for it
- Optimized IDA* (keep track of current search path with stack)
- Peer review #2
- Updated documention

## Thoughts & Comments

I finally found optimization for IDA* which increased its performance over A* algorithm. I started to keep track of visited nodes in current path. I don't know how I missed this first, even though it is clearly stated in the [Wikipedia](https://en.wikipedia.org/wiki/Iterative_deepening_A*) articles pseudocode.

## Next Week

- Documentation
- Increasing test coverage