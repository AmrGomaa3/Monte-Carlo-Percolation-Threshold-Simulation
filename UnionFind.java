
public class UnionFind {
    
    private int[] id;
    private int[] sz;

    public UnionFind(int size) {
        this.id = new int[size];
        this.sz = new int[size];

        for (int i = 0; i < size; i++) {
            this.id[i] = i;
            this.sz[i] = 1;
        }
    }

    public int findRoot(int node) {
        while (node != this.id[node]) {
            this.id[node] = this.id[this.id[node]];
            node = this.id[node];
        }

        return node;
    }

    public void union(int first, int second) {
        int rootOfFirst = this.findRoot(first);
        int rootOfSecond = this.findRoot(second);

        if (rootOfFirst == rootOfSecond) {
            return;
        }

        if (this.sz[rootOfFirst] < this.sz[rootOfSecond]) {
            this.id[rootOfFirst] = rootOfSecond;
            this.sz[rootOfSecond] += this.sz[rootOfFirst];
        } else {
            this.id[rootOfSecond] = rootOfFirst;
            this.sz[rootOfFirst] += this.sz[rootOfSecond];
        }
    }

    public boolean connected(int first, int second) {
        return this.findRoot(second) == this.findRoot(first);
    }
}
