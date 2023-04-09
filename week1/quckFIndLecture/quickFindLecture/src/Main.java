import edu.princeton.cs.algs4.StdIn.*;
public class Main {
    public static void main(String[] args) {
        QuickFindUF UF = new QuickFindUF(9);
        UF.union(3, 4);
        UF.union(3, 8);
        System.out.println(UF.isConnected(3,4));
        System.out.println(UF.isConnected(2,1));
        UF.printID();
    }
}

