import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final boolean[] grid;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufNoBottom;
    private int numberOfOpenSites = 0;
    private void checkForBounds(int row, int col) {
        if ((row <= 0) || (col <= 0) || (row > n) || (col > n)) {
            throw new IllegalArgumentException();
        }
    }
    private int convertCordsToIndex(int row, int col) {
        checkForBounds(row, col);
        return (((row - 1) * this.n) + (col- 1));
    }
    private boolean isConnected(int p, int q, WeightedQuickUnionUF someUf) {
        return someUf.find(p) == someUf.find(q);
    }
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <=0)  {
            throw new IllegalArgumentException();
        }
        this.grid = new boolean[(n*n) + 2];
        this.n = n;
        this.uf = new WeightedQuickUnionUF(grid.length);
        this.ufNoBottom = new WeightedQuickUnionUF(grid.length -1);
        for (int i =1; i < grid.length - 2 ; i++) {
            grid[i] = false;
        }
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        //Get the index of the site to check
        int indexToCheck = convertCordsToIndex(row, col);

        //If site is open return
        if (grid[indexToCheck]) {
            return;
        }
        if (col + 1 <= n) {
            if (grid[indexToCheck + 1]) {
                this.uf.union(indexToCheck , indexToCheck + 1);
                this.ufNoBottom.union(indexToCheck , indexToCheck + 1);
            }
        }
        if (col - 1 >= 1) {
            if (grid[indexToCheck -1]) {
                this.uf.union(indexToCheck, indexToCheck -1);
                this.ufNoBottom.union(indexToCheck , indexToCheck - 1);
            }
        }
        if (indexToCheck + n < grid.length - 1) {
            if (grid[indexToCheck + n]) {
                this.ufNoBottom.union(indexToCheck , indexToCheck + n);
                this.uf.union(indexToCheck, indexToCheck + n);
            }
        }
        if (indexToCheck - n >= 0) {
            if (grid[indexToCheck - n]) {
//                System.out.println("neighbour: " + (indexToCheck - n));
                this.ufNoBottom.union(indexToCheck , indexToCheck - n);
                this.uf.union(indexToCheck, indexToCheck - n);
            }
        }
        //Open the site
        this.grid[indexToCheck] = true;
        //Increase the number of open sites
        this.numberOfOpenSites ++;
        //Connect it with virtual top is row is 1
        if (row == 1) {
            this.uf.union(indexToCheck, 0);
            this.ufNoBottom.union(indexToCheck, 0);
        }
        //Connect with the virtual bottom if row is n
        if (row == n) {
//            System.out.println("connecting with virtual bottom");
            this.uf.union(indexToCheck, grid.length-1);
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int indexToCheck = convertCordsToIndex(row, col);
        return this.grid[indexToCheck];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int indexToCheck = convertCordsToIndex(row, col);
        return isConnected(indexToCheck, 0, this.ufNoBottom) && isOpen(row, col);
    }
    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }
    // does the system percolate?
    public boolean percolates() {
        return isConnected(0, grid.length - 1, this.uf);
    }
    // test client (optional)
    public static void main(String[] args) {
        Percolation test = new Percolation(5);
//        test.open(1,3);
        test.open(2,3);
        test.open(3,3);
        test.open(3,1);
        System.out.println("is full: " + test.isFull(3,3));
        System.out.println("percolates: " + test.percolates());

//        test.open(2,1);
//        test.open(1,1);
//        test.open(5,5);
//        System.out.println("is open: " + test.isOpen(2,1));
//        test.open(1,4);
//        System.out.println("Is site at (3,1) open?: " + test.isOpen(3,1));
//        System.out.println("Is site at (3,4) open?: " + test.isOpen(3,4));
//        System.out.println("Number of open sites: " + test.numberOfOpenSites());
//        System.out.println("Is the group that has site 4,4 full?: " + test.isFull(4,4));
//        System.out.println("Is the group that has site 4,4 full?: " + test.isFull(2,1));
//        System.out.println("Does the system percolate ?: " + test.percolates());

//      _____1__2__3__4__5
//   1   |   0  1  2  3  4
//   2   |   5  6  7  8  9
//   3   |  10 11 12 13 14
//   4   |  15 16 17 18 19
//   5   |  20 21 22 23 24
    }
}
