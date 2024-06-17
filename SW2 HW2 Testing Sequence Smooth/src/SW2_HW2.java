import components.sequence.Sequence;
import components.sequence.Sequence1L;
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
public final class SW2_HW2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW2() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     *
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */

    /*
     * Redesign the method so that it is a function that returns the new
     * (smoothed) sequence instead of replacing a parameter. You need to modify
     * the method header and update the formal contract to reflect the changes.
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        Sequence<Integer> s3 = new Sequence1L<>();

        for (int i = 0; i < s1.length() - 1; i++) {
            int curr = (s1.entry(i) + s1.entry(i + 1)) / 2;
            s3.add(s3.length(), curr);
        }

        return s3;
    }

    //recursive

    public static Sequence<Integer> smoothRecursive(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        Sequence<Integer> s3 = new Sequence1L<>();

        if (s1.length() > 1) {
            int curr = s1.entry(0);
            int next = s1.entry(1);
            int avg = (curr + next) / 2;
            s1.remove(0);
            smoothRecursive(s1, s2);
            s3.add(s3.length(), avg);
            s1.add(0, curr);
        }

        return s3;

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
