package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /**
     * 0: closed
     * 1: open
     */
    private int[][] representation;
    private int size;
    /**
     * union(int p, int q)
     * connected(int p, int q)
     * find(int p)
     */
    private WeightedQuickUnionUF UNION;
    private int openSites;

    public Percolation(int N) {
        size = N;
        representation = new int[N][N];
        for (int[] col: representation) {
            for (int i = 0; i < N; i++) {
                col[i] = 0;
            }
        }
        UNION = new WeightedQuickUnionUF(N * N);
        openSites = 0;
    }
    // create N-by-N grid, with all sites initially blocked
    // bigO(N2)





    public void open(int row, int col) {
        throwErrors(row, col);
        representation[row][col] = 1;
        openSites += 1;
        connect(row,col);
    }
    // open the site (row, col) if it is not open already
    // Runtime theta(1)


    public boolean isOpen(int row, int col) {
        throwErrors(row, col);
        return representation[row][col] == 1 || representation[row][col] == 2;
    }
    // is the site (row, col) open?
    // Runtime theta(1)


    public int numberOfOpenSites() {
        return openSites;
    }
    // number of open sites

    //TODO improve efficiency
    public boolean percolates() {
        boolean result = false;
        int k = size - 1;
        for (int i = 0; i < size; i++) {
            result = result || (isFull(k, i));
            if (result == true) {
                break;
            }
        }
        return result;
    }
    // does the system percolate?

    //TODO increase efficiency
    public boolean isFull(int row, int col) {
        int ind = twoDToOneD(row, col);
        boolean result = false;
        for (int i = 0; i < size * size; i += 10) {
            result = result || (UNION.connected(ind, i) & isOpen(row, col));
            if(result == true){
                break;
            }
        }
        return result;
    }
    // is the site (row, col) full?

    public int getSize(){
        return size;
    }





    private boolean isOutOfBounds(int row, int col) {
        return row >= size || col >= size;
    }

    private boolean isIllegalCoors(int row, int col) {
        return row < 0 || col < 0;
    }

    private void throwErrors(int row, int col) {
        if (isOutOfBounds(row, col)) {
            throw new java.lang.ArrayIndexOutOfBoundsException("Coordinates out of Bounds");
        }
        if (isIllegalCoors(row, col)) {
            throw new java.lang.IllegalArgumentException("X and/or Y coordinates less than 0");
        }
    }

    int twoDToOneD(int row, int col) {
        throwErrors(row, col);
        return row + size * col;
    }

    void connect(int row, int col) {
        connectLeft(row, col);
        connectRight(row, col);
        connectTop(row, col);
        connectBot(row, col);
    }

    private void connectLeft(int row, int col){
        throwErrors(row, col);
        if(row == 0) {
            return;
        } else {
            if (isOpen(row - 1, col)) {
                int left = twoDToOneD(row - 1, col);
                int curr = twoDToOneD(row, col);
                UNION.union(curr, left);
            }
        }
    }

    private void connectRight(int row, int col){
        throwErrors(row, col);
        if(row == size - 1) {
            return;
        } else {
            if (isOpen(row + 1, col)) {
                int right = twoDToOneD(row + 1, col);
                int curr = twoDToOneD(row, col);
                UNION.union(curr, right);
            }
        }
    }

    private void connectTop(int row, int col){
        throwErrors(row, col);
        if(col == 0) {
            return;
        } else {
            if (isOpen(row, col - 1)) {
                int top = twoDToOneD(row, col - 1);
                int curr = twoDToOneD(row, col);
                UNION.union(curr, top);
            }
        }
    }

    private void connectBot(int row, int col){
        throwErrors(row, col);
        if(col == size - 1) {
            return;
        } else {
            if (isOpen(row, col + 1)) {
                int bot = twoDToOneD(row, col + 1);
                int curr = twoDToOneD(row, col);
                UNION.union(curr, bot);
            }
        }
    }

    WeightedQuickUnionUF getUNION(){
        return UNION;
    }
}
