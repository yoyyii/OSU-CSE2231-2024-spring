import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class SW2_HW8 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW8() {
    }

    /**
     * Finds {@code x} in {@code q} and, if such exists, moves it to the front
     * of {@code q}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be searched
     * @param x
     *            the entry to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if <x> is substring of q
     *  then <x> is prefix of q
     * </pre>
     */
    private static <T> void moveToFront(Queue<T> q, T x) {

        Queue<T> target = q.newInstance();
        Queue<T> original = q.newInstance();

        for (int i = 0; i < q.length(); i++) {
            T curr = q.dequeue();

            if (curr.equals(x)) {
                target.enqueue(curr);
            } else {
                original.enqueue(curr);
            }
        }
        target.append(original);
        q.transferFrom(target);

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
