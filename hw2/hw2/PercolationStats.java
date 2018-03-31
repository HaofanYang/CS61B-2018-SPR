package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private Percolation world;
    private int currentTime = 0;
    private double[] results;
    private int size;
    private int numOfSim;


    public PercolationStats(int N, int T, PercolationFactory pf) {
        results = new double[T];
        size = N;
        numOfSim = T;
        for (int i = 0; i < T; i++){
            world = pf.make(N);
            results[i] = compute(world);
        }
    }
    // perform T independent experiments on an N-by-N grid

    private double compute(Percolation pc) {
        int result = 0;
        int size = pc.getSize();
        while (true) {
            int x = StdRandom.uniform(size);
            int y = StdRandom.uniform(size);
            pc.open(x, y);
            if (pc.percolates()){
                break;
            }
        }
        return pc.numberOfOpenSites();
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
        return (mean() - 2 * stddev());

    }
    // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return mean() + 2 * stddev();
    }

    public void show(){
        System.out.println("For " + size + " * " + size + " space, " + " the critical probability is "
                + mean()/(size * size) + " +/- " + stddev() / (size * size) + " ," + confidenceLow() / (size * size) + " - " + confidenceHigh() / (size * size));
    }

}
