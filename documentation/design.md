# Design document

The fifteen-puzzle-solver is an application for solving [15-puzzle](https://en.wikipedia.org/wiki/15_puzzle) with different algorithms. Idea is to find solution with as few moves as possible.

## Algorithms

I choosed A* -algorithm to solve 15-puzzle because being optimized (heuristic) breadth-first algorithm, I supposed it to find shortest path to solution efficiently. I used A* with two different heuristics:

- Heuristic 1: calculate tiles which are on wrong position
- Heuristic 2: calculate sum of tiles manhattan distance between current and correct position

## Time and space complexity

Implemented A* -algorithm is basically Dijkstras -algorithm with heuristics. Let us denote G = (V, E), where G is a game-tree, V is set of vertexes (game states) and E is set of edges (childrens of current game state). Dijkstras algorithm ...

Time complexity of A* -algorithm depends a lot of heuristic used. Both of the used heuristics loop once trough the game board so time-complexity for both of them is O(n^2), where n is width of the game board (in 15-puzzle: n=4).

## Data structures

Following data structures are needed for implementation of an A* -algorithm:

- PriorityQueue for traversing the game-tree
- HashSet for keeping track visited game states
- ArrayList for storing game states following current state

Also Array is needed for storing state of each game.

## Sources

- Cormen, Thomas H et al. (2009). Introduction to Algorithms. The MIT Press.