import components.sequence.Sequence;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
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
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        // TODO - fill in body

        s2.clear();

        //implement 1

        for (int i = 0; i < s1.length() - 1; i++) {
            int curr = s1.entry(i);
            int next = s1.entry(i + 1);

            double avg = 0;

            if (curr % 2 == 0) {
                avg += curr / 2;
            } else if (curr > 0) {
                avg += (curr - 1) / 2.0;
                avg += 0.5;
            } else {
                avg += (curr + 1) / 2.0;
                avg -= 0.5;
            }

            if (next % 2 == 0) {
                avg += next / 2;
            } else if (next > 0) {
                avg += (next - 1) / 2.0;
                avg += 0.5;
            } else {
                avg += (next + 1) / 2.0;
                avg -= 0.5;
            }

            int result = (int) avg;

            s2.add(s2.length(), result);
        }

        //implement 2

//        if (s1.length() > 1) {
//            int curr = s1.entry(0);
//            int next = s1.entry(1);
//            int avg = (curr + next) / 2;
//            s1.remove(0);
//            smooth(s1, s2);
//            s2.add(0, avg);
//            s1.add(0, curr);
//        }

    }

}