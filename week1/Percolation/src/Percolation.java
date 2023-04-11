import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {

    private final int n;
    private final boolean[] grid;
    private final WeightedQuickUnionUF uf;
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
    private boolean isConnected(int p, int q) {
        return this.uf.find(p) == this.uf.find(q);
    }
    private int findRow(int index) {
        for (int i = 0; i<n + 1; i++) {
            if (((this.n * i) - index) > 0) {
                return i;
            }
        }
        return  0;
    }
    private boolean isTheSameRow(int p, int q) {
        for (int i = 0; i < this.n; i++) {
            if (findRow(p) == findRow(q)) {
                return true;
            }
        }
        return false;
    }
//    private int[] getIndexesOfOpenedNeighbours(int indexToCheck) {
//        int[] neighbourIndexes = new int[4];
//        if (isTheSameRow(indexToCheck, indexToCheck + 1) && (indexToCheck + 1 < this.grid.length)) {
//            neighbourIndexes[0] = indexToCheck + 1;
//        }
//        else {
//            neighbourIndexes[0] = -1;
//        }
//        if (isTheSameRow(indexToCheck, indexToCheck - 1) && (indexToCheck - 1 >= 0)) {
//            neighbourIndexes[1] = indexToCheck -1;
//        }
//        else {
//            neighbourIndexes[1] = -1;
//        }
//        if (indexToCheck + n < grid.length - 1) {
//            neighbourIndexes[2] = indexToCheck + n ;
//        } else {
//            neighbourIndexes[2] = -1;
//        }
//        if (indexToCheck - n > 0) {
//            neighbourIndexes[3]= indexToCheck - n;
//        } else {
//            neighbourIndexes[3] = -1;
//        }
//
//        return neighbourIndexes;
//    }
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <=0)  {
            throw new IllegalArgumentException();
        }
        this.grid = new boolean[(n*n) + 2];
        this.n = n;
        this.uf = new WeightedQuickUnionUF(grid.length);
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
        //Get an array of neighbour indexes
//        int[] neighbourIndexes = getIndexesOfOpenedNeighbours(indexToCheck);
        //Loop through neighbour indexes. If neighbour site is open, union open site with user provided index (connect them)
//        for (int neighbourIndex : neighbourIndexes) {
//            if (neighbourIndex != -1) {
//                if (grid[neighbourIndex]) {
//                    this.uf.union(neighbourIndex, indexToCheck);
//                }
//            }
//        }
        System.out.println("Opening " + indexToCheck);
        if (isTheSameRow(indexToCheck, indexToCheck + 1) && (indexToCheck + 1 < this.grid.length)) {
            if (grid[indexToCheck + 1]) {
//                System.out.println("neighbour: " + (indexToCheck + 1));
                this.uf.union(indexToCheck , indexToCheck + 1);
            }
        }
        if (isTheSameRow(indexToCheck, indexToCheck - 1) && (indexToCheck - 1 >= 0)) {
            if (grid[indexToCheck -1]) {
//                System.out.println("neighbour: " + (indexToCheck - 1));
                this.uf.union(indexToCheck, indexToCheck -1);
            }
        }

        if (indexToCheck + n < grid.length - 1) {
            if (grid[indexToCheck + n]) {
//                System.out.println("neighbour: " + (indexToCheck + n));

                this.uf.union(indexToCheck, indexToCheck + n);
            }
        }

        if (indexToCheck - n >= 0) {
            if (grid[indexToCheck - n]) {
//                System.out.println("neighbour: " + (indexToCheck - n));

                this.uf.union(indexToCheck, indexToCheck - n);
            }
        }

            //Open the site
        this.grid[indexToCheck] = true;
        //Increase the number of open sites
        this.numberOfOpenSites ++;
        //Connect it with virtual top is row is 1
        if (findRow(indexToCheck) == 1) {
            this.uf.union(indexToCheck, 0);
        }
        //Connect with the virtual bottom if row is n
        if (findRow(indexToCheck) == n) {
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
        return isConnected(indexToCheck, 0) && isOpen(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return isConnected(0, grid.length - 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        test.open(5,5);
//        test.open(1,3);
        test.open(4,5);
//        test.open(3,3);
//        test.open(3,4);
//        test.open(4,4);
//        test.open(5,4);
//        test.open(5,5);
//        test.open(1,4);
        System.out.println("is open: " + test.isOpen(2,1));
        System.out.println("is full: " + test.isFull(5,5));
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
