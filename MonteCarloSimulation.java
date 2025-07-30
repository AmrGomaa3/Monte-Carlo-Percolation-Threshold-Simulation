
import java.util.Random;

public class MonteCarloSimulation {
    
    private UnionFind UF;
    private int grid;
    private Random rand;
    private boolean display;
    private boolean[] open;

    public MonteCarloSimulation(int grid) {
        this.grid = grid;
        this.UF = new UnionFind((grid * grid) + 2); // add two extra nodes for top and bottom connectionn (indexing from 1 to gridxgrid)
        this.rand = new Random();
        this.display = false;
        this.open = new boolean[(grid * grid) + 2];

        // connect the top and bottom rows to the virtual nodes
        for (int i = 1; i <= this.grid; i++) {
            this.UF.union(i, 0);
            this.UF.union((grid * grid) + 1 - i, (grid * grid) + 1);
        }
    }

    public void enableDisplay() {
        this.display = true;
    }

    public void disableDisplay() {
        this.display = false;
    }

    private boolean checkPath() {
        return this.UF.connected(0, (this.grid * this.grid) + 1); // check if a connection exists between the virtual top and bottom nodes
    }

    private int[] getNeighbours(int first) {

        // initialise all neighbours to -1
        int neighbour1 = -1, neighbour2 = -1, neighbour3 = -1, neighbour4 = -1;

        // check the existence of neighbours
        if (first <= (this.grid * this.grid) - this.grid) {
            neighbour1 = first + this.grid;
        }

        if (first > this.grid) {
            neighbour2 = first - this.grid;
        }

        if (first % this.grid != 0) {
            neighbour3 = first + 1;
        }

        if (first % this.grid != 1) {
            neighbour4 = first - 1;
        }

        int[] neighboursArray = {neighbour1, neighbour2, neighbour3, neighbour4};

        return neighboursArray;
    }

    private int connectPath() {
        int trials = 0;

        while (!this.checkPath()) {
            int randomNode = this.rand.nextInt(this.grid * this.grid) + 1; // choose a random node
            // skip if the node is already opened
            if (this.open[randomNode]) {
                continue;
            }

            this.open[randomNode] = true; // open the randomly selected node

            int[] neighbours = this.getNeighbours(randomNode); // get the neighbours of the randomly selected node

            // connect the randomly selected node to the open neighbours
            for (int i = 0; i < 4; i++) {
                if (neighbours[i] > 0) {
                    if (this.open[neighbours[i]]) {
                        this.UF.union(randomNode, neighbours[i]);
                    }
                }
            }

            trials++;

            // optional display of the grids
            if (this.display) {
                this.printGrid();
                System.out.println();
            }
        }

        return trials;
    }

    public double connectivityThreshold() {
        return (double) this.connectPath() / (this.grid * this.grid);
    }

    public void printGrid() {
        int n = this.grid;

        // Top border
        System.out.print("+");
        for (int i = 0; i < n; i++) {
            System.out.print("---+");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("|");
            for (int j = 0; j < n; j++) {
                int index = i * n + j + 1; // skip virtual node 0

                boolean isOpen = this.open[index];  // only show cell as open if it was visited

                System.out.print(" " + (isOpen ? " " : "X") + " |");
            }
            System.out.println();

            // Horizontal separator after each row
            System.out.print("+");
            for (int j = 0; j < n; j++) {
                System.out.print("---+");
            }
            System.out.println();
        }
    }
}
