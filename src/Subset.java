import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *  The {@code RandomizedQueue} class provides a  client test that takes a command-line integer k;
 *  reads in a sequence of N strings from standard input using StdIn.readString(); and prints out
 *  exactly k of them, uniformly at random.
 *  Each item from the sequence can be printed out at most once. We may assume that 0 ≤ k ≤ n,
 *  where n is the number of string on standard input.
 *
 *  @author Jorge Frisancho
 */
public class Subset {

    /**
     *  This client test takes a command-line integer k; reads in a sequence of N strings from standard input;
     *  and prints out exactly k of them, uniformly at random.
     *
     *  @param args main function standard arguments
     */
    public static void main(String[] args) {
        RandomizedQueue<String> stack = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            stack.enqueue(StdIn.readString());
        }

        for (int k = Integer.parseInt(args[0]); k > 0; k--)
            StdOut.println(stack.dequeue());
    }
}