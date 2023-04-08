import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class RandomWord {
    public static void main(String[] args){
        String champion = new String();
        int i = 0;
        while (!StdIn.isEmpty())  {
            if (StdRandom.bernoulli(1/(i+1.0))) {
                champion = StdIn.readString();
            }
            i++;
        }
        StdOut.print(champion);
    }
}
