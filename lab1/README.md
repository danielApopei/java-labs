# Lab1

## Compulsory

- print "Hello World"
- basic adding of different base numbers using parsing

## Homework

- check if a number is k-reducible
  - the process of finding the next value can loop
  - store partial results in a vector
  - if value is met again, then it is a loop and I have not found k, return FALSE;
  - if k is found along the way starting from x, return TRUE;
- run program with parameters
  - works from command line
  - can also modify IntelliJ Environment to run with params
- show running time
  - using System.nanoTime();
  - getting a point in time at beginning and end of algorithm
  - the running time is the difference

## Bonus

- showing the adjecany matrix of wheel graph Wn.
  - first, we connect node 0 (the hub) to every other node
  - then, we connect nodes 1-2, 2-3, ..., n-1-1
- calculating the number of cycles
  - we use a dfs algorithm while building a cycle on the way there
  - when we can access the startingPoint once again, then we have found a cycle
  - after, we remove duplicates which may arise from starting the dfs from two different nodes of the same cycle (we divide by the length)