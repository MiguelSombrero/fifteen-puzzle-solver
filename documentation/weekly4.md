# Week 4

- [Hours](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/hours.md)

## Synopsis

- Implemented PriorityQueue data structure + tests
- Implemented IDA* algorithm
- First benchmarkings
- Updated test and implementation documents

## Problems

Not really.

## Questions & comments

I implemented IDA* algorithm and after some benchmarks, it seems to be slowest one to solve puzzles. Even though not using much memory, it consumes too much time with hard puzzles. Well, it is deapth-first search without trace of visited nodes, so I guess it's slow. Have to investigate though is it possible to optimize somehow.

Wondering also what is the correct way to implement PriorityQueue, which takes Comparator class as an attribute. Should the PriorityQueue class also implement Comparable, or is it enough just use the Comparator when comparing elements in the queue. In Tietorakenteet ja Algoritmit course material says: "Jos haluamme tallentaa PriorityQueue-rakenteeseen omia olioitamme, meidän tulee toteuttaa luokkaan metodi compareTo ja merkita, etta luokka toteuttaa rajapinnan Comparable."

## Next Week

- Testing, implementation and user guide documentation
- JavaDocs for PriorityQueue
- Peer review
- Tests for IDA* and A* algorithms
- Optimization for IDA* if possible