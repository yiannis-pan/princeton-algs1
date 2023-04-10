import com.sun.jdi.event.StepEvent;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdRandom.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private int n;
    private boolean[] grid;

    private WeightedQuickUnionUF uf;
    private int convertCordsToIndex(int row, int col) {
        return (((row - 1) * this.n) + (col- 1));
    }

    public boolean isConnected (int p, int q) {
        if (this.uf.find(p) == this.uf.find(q)) { return true;}
        return false;
    }

    private int findRow(int index) {
        for (int i = 0; i <n; i++ ){
            if (((this.n * i) - index) > 0) {
                return i;
            }
        }
        return  0;
    }

    private boolean isTheSameRow(int p, int q) {
        int row = 0;
        for (int i = 0; i < this.n; i++) {
            if (findRow(p) == findRow(q)) {
                return true;
            }
        }
        return false;
    }

    private void printArr() {
        for (int i =0; i < (n * n) -1; i++) {
            System.out.println(i);
        }
    }


    public int[] getIndexesOfOpenedNeighbours (int indexToCheck) {

        int[] neighbourIndexes = new int[4];

        if (isTheSameRow(indexToCheck, indexToCheck + 1)) {neighbourIndexes[0] = indexToCheck + 1;} else {neighbourIndexes[0] = -1;}
        if (isTheSameRow(indexToCheck, indexToCheck - 1)) {neighbourIndexes[1] = indexToCheck -1;} else {neighbourIndexes[1] = -1;}
        if (indexToCheck + n < grid.length) {neighbourIndexes[2]= indexToCheck + n;} else {neighbourIndexes[2] = -1;}
        if (indexToCheck - n < grid.length) {neighbourIndexes[3]= indexToCheck - n;} else {neighbourIndexes[3] = -1;}

        return neighbourIndexes;
    }


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.grid = new boolean[(n*n -1)];
        this.n = n;
        this.uf = new WeightedQuickUnionUF(grid.length);
        for (int i =0; i < (n * n) -1; i++) {
            grid[i] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        //Get the index of the site to check
        int indexToCheck = convertCordsToIndex(row, col);
        //If site is open return
        if (grid[indexToCheck]) {return;}
        //Get an array of neighbour indexes
        int[] neighbourIndexes = getIndexesOfOpenedNeighbours(indexToCheck);
        //Loop through neighbour indexes. If neighbour site is open, union open site with user provided index (connect them)
        for (int i = 0; i < neighbourIndexes.length -1; i++) {
            if (grid[neighbourIndexes[i]] && neighbourIndexes[i] != -1) {
                this.uf.union(neighbourIndexes[i], indexToCheck);
            }
        }
        //Open the site
        this.grid[indexToCheck] = true;


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int indexToCheck = convertCordsToIndex(row, col);
        if (this.grid[indexToCheck]) {return  true;}
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        test.open(2,3);
        int[] neighbourTest = test.getIndexesOfOpenedNeighbours(7);
        for (int i: neighbourTest
             ) {
            System.out.println(i);
        }
        test.open(2,4);
        test.open(2,2);
        test.open(1,3);

        System.out.println(test.isConnected(2, 8));
    }
}
