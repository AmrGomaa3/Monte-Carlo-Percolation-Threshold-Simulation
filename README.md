# Monte Carlo Percolation Threshold-Simulation
---
This project estimates the percolation threshold on an n × n grid using a Monte Carlo simulation powered by the Union-Find data structure.
---
## Algorithm Overview
1. Initialize an n × n grid of blocked sites.
2. Randomly open one blocked site at a time.
3. Use a Union-Find (weighted quick union) data structure to track site connectivity.
4. After each opening, check whether a path exists from the top row to the bottom.
5. Record the number of open sites when the system percolates.
6. Repeat for `T` trials and compute the average threshold: 
   `threshold = open_sites / (n × n)`
---
## Technical Details
- **Language**: Java
- **Data Structure**: Weighted Quick Union with path compression
- Connected node `0` to all sites in the top row
- Connected node `n² + 1` to all sites in the bottom row
- Percolation occurs when `connected(0, n² + 1)` returns `true`
- **Optional**: `printGrid()` for visual ASCII rendering of the percolation process
---
## Project files
- `UnionFind.java`: Union-Find implementation with optimizations.
- `MonteCarloSimulation.java`: Core simulation engine.
- `PercolationSimulator.java`: Runs trials and outputs results.
---
## How to Run
Compile and run using any Java 11+ environment:

```bash
javac *.java
java PercolationSimulator (grid size) (number of trials) [-show]
```
---
## Example
Run using a 100x100 grid for 10000 trials
```bash
java PercolationSimulator 100 10000
```
Output:
```bash
Trial executed 10000 times, with a 100x100 grid
Connectivity threshold = 0.592765179999999
Elapsed time = 4.1271534 seconds
```
---
## Notes
- For large number of trials, do not use the `-show` flag to avoid perfromance degradations
- If using `-show`, pipe the output for easier viewing:
Example:
```bash
java PercolationSimulator 10 1 -show | less
```
---
See the [LICENSE](./LICENSE) file for license information.
