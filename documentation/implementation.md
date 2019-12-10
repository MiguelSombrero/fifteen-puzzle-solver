# Implementation

I implemented two different algorithms for solving 15-puzzles: A* and IDA* algorithms. I used three different heuristics for both of them: position based heuristic, manhanttan distance heuristic and manhattan distance + linear collision heuristics.

## Project structure

I divided application into four (five if you count main package) packages: *ui* for user interface, *service* for providing methods for solving and generating puzzles, *domain* for puzzles, puzzle solvers and heuristics and *utils* for data structures and puzzle generator. 

![structure](https://github.com/MiguelSombrero/fifteen-puzzle-solver/blob/master/documentation/pics/structure.jpg)

## Time-Complexity

### A* algorithm

Time-complexity of A* algorithm was after all O(lgV*V), where V is the set of game states, even if in design document is stated O(V^2). I implemented PriorityQueue with binary min-heap, which takes O(lgV) for adding and extracting elements. Algorithm loops trough game states visiting every vertex only one, taking O(V) time. With every vertex, algorithm extracts and possibly adds element to the queue, taking O(lgV) time. Algorithm also loops through neighbours of vertices, and the total number of neighbours is |E|. Other operations take constant time.

Therefore overall time-complexity for A* is O(lg(V) * V + E) = O(lg(V)*V).

### IDA* algorithm

Time-complexity of iterative deepening depth-first search (IDDFS) algorithm is O(b^d), where b is the branching factor and d is the deapth of the goal (Iterative Deepening depth-first search, Wikipedia). Since branching factor in 15-puzzle is 3 on average, and depth of the goal always <= 80, we have worst case time-complexity of O(3^80). However - with good heuristic - algorithm performs way better than this in practise. IDA* algorithm adds calculation of heuristic to IDDFS, but other operations take constant time. Calculation of heuristic takes maximum of n^4 operations, where n = 4. Note that b^d is just another way of stating possible game states, and is factually same as V in A* case.

Therefore overall time-complexity for IDA* is O(n^4 * b^d) = O(b^d) = O(V).

## Space-complexity

### A* algorithm

Space-compexity of the A* algorithm stayed O(V + V) = O(V) as stated in the design document.

### IDA* algorithm

IDA* algorithm doesn't keep track of the visited nodes nor the nodes to be visited. It only tracks current search path, which is limited to 80 moves. Therefore space-complexity of the IDA* algorithm is linear in the length of the solution that it constructs (Iterative Deepening A*, Wikipedia). Since search path is limited to 80 moves (constant), IDA* algorithms space-complexity is O(80) = O(1).

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
- Iterative Deepening depth-first search. (2019). Wikipedia. [cited: 10.12.2019]. https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search