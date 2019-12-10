# Design document

The fifteen-puzzle-solver is an application for solving [15-puzzle](https://en.wikipedia.org/wiki/15_puzzle) with different algorithms. Idea is to find solution with as few moves as possible.

## Algorithms

I choosed A* -algorithm to solve 15-puzzle because being optimized (heuristic) breadth-first algorithm, I supposed it to find shortest path to solution efficiently. I used A* with 3 different heuristics:

- Heuristic 1: calculate tiles which are on wrong position
- Heuristic 2: calculate sum of tiles manhattan distance between current and correct position
- Heuristic 3: calculate manhattan distance (see abowe) plus linear collision of the tiles

## Time-complexity

Implemented A* -algorithm is basically Dijkstras -algorithm with heuristics. Let us denote G = (V, E), where G is a game-tree, V is set of vertices (game states) and E is set of edges (possible state transitions between game states). Dijkstra's algorithm loops trough game states, visiting every game state only once - taking O(V) time. With every vertex, algorithm extracts minimum game state from priority queue - taking again O(V) time. With every vertex, algorithm also loops trough its neighbors. Since every vertex is processed only once, total number of neighbors to be examined is |E|. Therefore Dijkstra's algorithm without heuristics take O(V^2 + E) = O(V^2) time.

Time complexity of A* -algorithm depends a lot of heuristic used. Both of the manhattan and position based heuristics loop once trough the game board so time-complexity for both of them is O(n^2), where n is width of the game board (in 15-puzzle: n=4). Linear collision heuristic loops game board twice so the time-complexity for this is O(n^4).

Heuristics adds only minor coefficient to time-complexity since width of the game board is constant (n = 4), so the overall time-complexity for A* is O(V * n^4 * V + E) = O(n^4 * V^2 + E) = O(V^2).

## Space-complexity

Algorithm keeps track for game states to visit and this can hold maximum of V states, since every state is visited only once. Algorithm also keeps track of visited game states, which can hold again maximum of V states. Other operations takes O(1), space so the overall space-complexity is O(V + V) = O(V).

## Data structures

Following data structures are needed for implementation of an A* -algorithm:

- PriorityQueue for traversing the game-tree
- HashSet for keeping track visited game states
- ArrayList for storing game states following current state

Also basic Array is needed for storing state of each game.

## Sources

- Cormen, Thomas H et al. (2009). Introduction to Algorithms. The MIT Press.