# Implementation

I implemented two different algorithms for solving 15-puzzles: A* and IDA* algorithms. I used three different heuristics for both of them: position based heuristic, manhanttan distance heuristic and manhattan distance + linear collision heuristics.

## Project structure

I divided application into four (five if you count main package) packages: *ui* for user interface, *service* for providing methods for solving and generating puzzles, *domain* for puzzles, puzzle solvers and heuristics and *utils* for data structures and puzzle generator. 

![structure](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/structure.jpg)

## Time-Complexity

### A* algorithm

Time-complexity of A* algorithm was after all O(lgV*V), where V is the set of game states, even if in design document is stated O(V^2). I implemented PriorityQueue with binary min-heap, which takes only O(lgV) time for adding and extracting elements. Algorithm loops trough game states visiting every vertex only one, taking O(V) time. With every vertex, algorithm extracts and possibly adds element to the queue, taking O(lgV) time. Algorithm also loops through neighbours of vertices, and the total number of neighbours is |E|. Therefore overall time-complexity for A* is O(lg(V) * V + E) = O(lg(V)*V).

### IDA* algorithm

UNDER CONSTRUCTION

## Space-complexity

### A* algorithm

Space-compexity of the A* algorithm stayed O(V + V) = O(V) as stated in the design document.

### IDA* algorithm

IDA* algorithm doesn't keep track of the visited nodes nor the nodes to be visited. Space-complexity of the IDA* algorithm is linear in the length of the solution that it constructs (Iterative Deepening A*, Wikipedia). Since I limited (pruned) search tree to 80 moves (constant), IDA* algorithms space-complexity is O(1).

## Possible improvements

### Optimization

A* algorithm is using lots of memory by saving all the game states it is about to visit in priority queue. If you could prune game states with some heuristic before saving them, this might save a lot of memory and make A* perform faster with harder puzzles (and without memory error).

Possibly some optimization could be made with linear collision heuristic. I haven't tested different methods for calculating linear collisions. 

### Reliability

PuzzleGenerator classes generateChildren() method loops sometimes for infinity. This is due of a situation, where current puzzles all children are already visited, and algorithm tryes to find one that is not. This situation doesn't happen too often and therefore I haven't fixed it (yet). 

## Sources

- Cormen, Thomas H et al. (2009). Introduction to Algorithms. The MIT Press.
- Laaksonen, Antti. (2018). Tietorakenteet ja algoritmit.
- Iterative Deepening A*. (2018). Wikipedia. [cited: 22.11.2019]. https://en.wikipedia.org/wiki/Iterative_deepening_A*