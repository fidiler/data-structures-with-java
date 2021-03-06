public class QuickUnionPathCompressionAll implements UF {
    private int count;
    private int[] rank, parent;

    QuickUnionPathCompressionAll(int size) {
        count = size;
        rank = new int[size];
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            rank[i] = 1;
            parent[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot]++;
        }

        count--;
    }

    @Override
    public int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("invalid p");
        }

        if (p == parent[p]) {
            return p;
        }

        parent[p] = find(parent[p]);

        return parent[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
