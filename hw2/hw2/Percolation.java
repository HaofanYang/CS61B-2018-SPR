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
    private int virtualTop;
    private int virtualBot;

    public Percolation(int N) {
        size = N;
        representation = new int[N][N];
        for (int[] col: representation) {
            for (int i = 0; i < N; i++) {
                col[i] = 0;
            }
        }
        UNION = new WeightedQuickUnionUF(N * N + 2);
        openSites = 0;
        virtualTop = N * N;
        virtualBot = N * N + 1;
        connectToVSites();
    }
    // create N-by-N grid, with all sites initially blocked
    // bigO(N2)


    public void open(int row, int col) {
        throwErrors(row, col);
        if (isOpen(row, col)){
            return;
        }
        representation[row][col] = 1;
        openSites += 1;
        connect(row,col);
    }
    // open the site (row, col) if it is not open already
    // Runtime theta(1)


    public boolean isOpen(int row, int col) {
        throwErrors(row, col);
        return representation[row][col] == 1;
    }
    // is the site (row, col) open?
    // Runtime theta(1)


    public int numberOfOpenSites() {
        return openSites;
    }
    // number of open sites


    public boolean percolates() {
        return UNION.connected(virtualBot, virtualTop);
    }
    // does the system percolate?


    public boolean isFull(int row, int col) {
        return isOpen(row, col) & UNION.connected(virtualTop, twoDToOneD(row, col));
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
        return col + size * row;
    }

    void connect(int row, int col) {
        connectLeft(row, col);
        connectRight(row, col);
        connectTop(row, col);
        connectBot(row, col);
    }

    private void connectLeft(int row, int col){
        throwErrors(row, col);
        if(col == 0) {
            return;
        } else {
            if (isOpen(row, col - 1)) {
                int left = twoDToOneD(row, col - 1);
                int curr = twoDToOneD(row, col);
                UNION.union(curr, left);
            }
        }
    }

    private void connectRight(int row, int col){
        throwErrors(row, col);
        if(col == size - 1) {
            return;
        } else {
            if (isOpen(row, col + 1)) {
                int right = twoDToOneD(row, col + 1);
                int curr = twoDToOneD(row, col);
                UNION.union(curr, right);
            }
        }
    }

    private void connectTop(int row, int col){
        throwErrors(row, col);
        if(row == 0) {
            return;
        } else {
            if (isOpen(row - 1, col )) {
                int top = twoDToOneD(row - 1, col);
                int curr = twoDToOneD(row, col);
                UNION.union(curr, top);
            }
        }
    }

    private void connectBot(int row, int col){
        throwErrors(row, col);
        if(row == size - 1) {
            return;
        } else {
            if (isOpen(row + 1, col)) {
                int bot = twoDToOneD(row + 1, col);
                int curr = twoDToOneD(row, col);
                UNION.union(curr, bot);
            }
        }
    }

    WeightedQuickUnionUF getUNION(){
        return UNION;
    }

    private void connectToVSites(){
        for (int i = 0; i < size; i++) {
            UNION.union(virtualTop, twoDToOneD(0, i));
            UNION.union(virtualBot, twoDToOneD(size - 1, i));
        }
    }
}
