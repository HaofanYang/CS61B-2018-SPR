package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] results;
    private int size;
    private int numOfSim;


    public PercolationStats(int N, int T, PercolationFactory pf) {
        results = new double[T];
        size = N;
        numOfSim = T;
        for (int i = 0; i < T; i++){
            results[i] = compute(pf.make(N));
        }
    }
    // perform T independent experiments on an N-by-N grid

    private double compute(Percolation pc) {
        int size = pc.getSize();
        while (!pc.percolates()) {
            int x = StdRandom.uniform(size);
            int y = StdRandom.uniform(size);
            pc.open(x, y);
        }
        return pc.numberOfOpenSites() / (double) (size * size);
    }

    public double mean() {
        return StdStats.mean(results);
    }
    // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(results);
    }
    // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(numOfSim));

    }
    // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(numOfSim));
    }

    public void show(){
        System.out.println("For " + size + " * " + size + " space: ");
        System.out.println("The critical probability is: "  + confidenceLow() + " -- " + confidenceHigh());
    }

}
