import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] percPoints;

    private final int trials;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        //Initialise Properties
        this.percPoints = new double[trials];
        this.trials = trials;
        int totalSites = (n * n) - 1;

        //Open random sites for n x n gird until it percolates. Do this trials times
        for (int i=0; i< trials; i++) {
            Percolation pc = new Percolation(n);
            while (!pc.percolates()) {
                pc.open(StdRandom.uniformInt(1, n +1), StdRandom.uniformInt(1,n + 1));
            }
            //When system percolates add the number of open sites it took to percolate on the percPoints array at the trial index
            percPoints[i] = ((double) pc.numberOfOpenSites() / ((double) ((n * n) - 1)));
            System.out.println("Total Sites: " + totalSites);
            System.out.println("System percolated at: " + pc.numberOfOpenSites() + " open sites" );
            System.out.println(percPoints[i]);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.percPoints);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.percPoints);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - (((1.96)/Math.sqrt(trials))) * Math.sqrt(stddev()));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + (((1.96)/Math.sqrt(trials))) * Math.sqrt(stddev()));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        if (n <= 0 || t<=0) {throw new IllegalArgumentException();}
        PercolationStats test = new PercolationStats(n, t);
        System.out.println("mean = " + test.mean());
        System.out.println("stddev = " + test.stddev());
        System.out.println("[ " + test.confidenceLo()  + ", " + test.confidenceHi() + "]");
    }

}