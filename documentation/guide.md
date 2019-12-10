# User manual

With 15-puzzle-solver you can generate and solve 15-puzzles with A* and IDA* algorithms using 3 different heuristics. On start up, application prints available options, which should be self-explanatory - but here is a little synopsis:

Optioens p? generates puzzles, which you can then solve with options s? or benchmark with options b?. In order to benchmark algorithm, you need to have multiple puzzles generated. 

- p0 = Generates one puzzle which minimun solution known to be 80 moves
- p1 = Generates n puzzles according to user input. Can be user for benchmarking (b1-b6) puzzles
- p2 = Generates one puzzle by changing n randomly chosen tiles. Can be used for solving (s1-s6) puzzle
- p3 = Generates one puzzle by moving n times backwards from solved state. Can be used for solving (s1-s6) puzzle

Options s? solves puzzle with given algorithm and given heuristic. Prints solved puzzle, time and moves taken. Algorithms and heuristics are:

- s1 = A* with position based heuristics
- s2 = A* with manhattan heuristics
- s3 = A* with manhattan + linear collision heuristics
- s4 = IDA* with position based heuristics
- s5 = IDA* with manhattan heuristics
- s6 = A* with manhattan + linear collision heuristics

Options b? solves n puzzles with given algorithm and given heuristic. Prints solved puzzle, average time and average moves taken. Algorithms and heuristics are:

- b1 = A* with position based heuristics
- b2 = A* with manhattan heuristics
- b3 = A* with manhattan + linear collision heuristics
- b4 = IDA* with position based heuristics
- b5 = IDA* with manhattan heuristics
- b6 = A* with manhattan + linear collision heuristics