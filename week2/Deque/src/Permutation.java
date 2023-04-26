import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
//        for (int i = 1; i < args.length; i++) {
//            rq.enqueue(args[i]);
//        }
        for (int i = 0; i < Integer.valueOf(args[0]); i ++){
            System.out.println(rq.dequeue());
        }
    }
}