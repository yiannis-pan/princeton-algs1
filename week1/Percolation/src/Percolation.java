import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n; //n is the number of rows and columns for the grid of sites.
    private final boolean[] grid; //The gird of sites.

    //The instance of the Weighted Quick Union Find Alg which is the size of the gird, with a virtual top and bottom.
    private final WeightedQuickUnionUF uf;

    //The instance of the Weighted Quick Union Find Alg which is used to check if a site is full.
    //To do this, id does not have a virtual bottom. See constructor for details.
    private final WeightedQuickUnionUF ufNoBottom;
    private int numberOfOpenSites = 0; //Number of Open Sites.

    //Func that checks if the user input is within task params.
    //If not it throws an IllegalArgumentException.
    private void checkForBounds(int row, int col) {
        if ((row <= 0) || (col <= 0) || (row > n) || (col > n)) {
            throw new IllegalArgumentException();
        }
    }

    //Func that returns the index of the site that corresponds to provided cords.
    //Checks first if cords are out of bounds
    private int convertCordsToIndex(int row, int col) {
        checkForBounds(row, col);
        return (((row - 1) * this.n) + (col- 1));
    }

    //Func that checks if two indexes are connected.
    //Finds the root element of each one and checks if they are the same.
    private boolean isConnected(int p, int q, WeightedQuickUnionUF someUf) {
        return someUf.find(p) == someUf.find(q);
    }
    // creates n-by-n grid, with all sites initially blocked.
    public Percolation(int n) {

        //Checks if n is positive.
        if (n <=0)  {
            throw new IllegalArgumentException();
        }

        //Init the gird of sites based on n and adding two more elements.
        //One for the virtual top, and one for the virtual bottom.
        this.grid = new boolean[(n*n) + 2];

        //Saves n
        this.n = n;

        //We have to define two instances of the data struct of the algo.
        //One that has the extra two sites, virtual top and virtual bottom and one that doesn't have the virtual bottom.
        //This because if a full site connects to the virtual bottom, then any other site that connects to the virtual bottom will be considered full.
        //To fix this, we use the instance of the struct that doesn't have the virtual bottom to check if a site is full.
        this.uf = new WeightedQuickUnionUF(grid.length);
        this.ufNoBottom = new WeightedQuickUnionUF(grid.length -1);

        //Filling with array with blocked sites
        for (int i =1; i < grid.length - 2 ; i++) {
            grid[i] = false;
        }
    }
    //Opens the site (row, col) if it is not open already.
    public void open(int row, int col) {
        //Get the index of the site to check.
        int indexToCheck = convertCordsToIndex(row, col);

        //If site is open return.
        if (grid[indexToCheck]) {
            return;
        }

        //If there is an open site to the right of site given we connect it.
        if (col + 1 <= n) {
            if (grid[indexToCheck + 1]) {
                this.uf.union(indexToCheck , indexToCheck + 1);
                this.ufNoBottom.union(indexToCheck , indexToCheck + 1);
            }
        }

        //If there is an open site to the left of site given we connect it.
        if (col - 1 >= 1) {
            if (grid[indexToCheck -1]) {
                this.uf.union(indexToCheck, indexToCheck -1);
                this.ufNoBottom.union(indexToCheck , indexToCheck - 1);
            }
        }

        //If there is an open site above the site given we connect it.
        if (indexToCheck + n < grid.length - 1) {
            if (grid[indexToCheck + n]) {
                this.ufNoBottom.union(indexToCheck , indexToCheck + n);
                this.uf.union(indexToCheck, indexToCheck + n);
            }
        }

        //If there is an open site bellow of site given we connect it.
        if (indexToCheck - n >= 0) {
            if (grid[indexToCheck - n]) {
                this.ufNoBottom.union(indexToCheck , indexToCheck - n);
                this.uf.union(indexToCheck, indexToCheck - n);
            }
        }

        //Open the site given.
        this.grid[indexToCheck] = true;

        //Increase the number of open sites.
        this.numberOfOpenSites ++;

        //Connect it with virtual top is row is 1.
        if (row == 1) {
            this.uf.union(indexToCheck, 0);
            this.ufNoBottom.union(indexToCheck, 0);
        }

        //Connect with the virtual bottom if row is n.
        //We only connect the main instance of the algo struct.
        //Again this is done so non-full sites that connect to the virtual bottom are connected with any full ones
        //That are connected to the virtual bottom
        if (row == n) {
            this.uf.union(indexToCheck, grid.length-1);
        }
    }
    //Is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int indexToCheck = convertCordsToIndex(row, col);
        return this.grid[indexToCheck];
    }
    //Is the site (row, col) full?
    //We use the instance of the algo struct that doesn't have the virtual bottom
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
        test.open(2,3);
        test.open(3,3);
        test.open(3,1);
        System.out.println("is full: " + test.isFull(3,3));
        System.out.println("percolates: " + test.percolates());

//      _____1__2__3__4__5
//   1   |   0  1  2  3  4
//   2   |   5  6  7  8  9
//   3   |  10 11 12 13 14
//   4   |  15 16 17 18 19
//   5   |  20 21 22 23 24

    }
}
