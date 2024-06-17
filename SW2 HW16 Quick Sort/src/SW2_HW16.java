import java.util.Comparator;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Yoyi Liao
 *
 */
public final class SW2_HW16 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW16() {
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

    /**
     * Partitions {@code q} into two parts: entries no larger than
     * {@code partitioner} are put in {@code front}, and the rest are put in
     * {@code back}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be partitioned
     * @param partitioner
     *            the partitioning value
     * @param front
     *            upon return, the entries no larger than {@code partitioner}
     * @param back
     *            upon return, the entries larger than {@code partitioner}
     * @param order
     *            ordering by which to separate entries
     * @clears q
     * @replaces front, back
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(#q, front * back)  and
     * for all x: T where (<x> is substring of front)
     *  ([relation computed by order.compare method](x, partitioner))  and
     * for all x: T where (<x> is substring of back)
     *  (not [relation computed by order.compare method](x, partitioner))
     * </pre>
     */
    private static <T> void partition(Queue<T> q, T partitioner, Queue<T> front,
            Queue<T> back, Comparator<T> order) {

        while (q.length() > 0) {
            T curr = q.dequeue();
            if (order.compare(curr, partitioner) > 0) {
                front.enqueue(curr);
            } else {
                back.enqueue(curr);
            }
        }
    }

    /**
     * Sorts {@code this} according to the ordering provided by the
     * {@code compare} method from {@code order}.
     *
     * @param order
     *            ordering by which to sort
     * @updates this
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(this, #this)  and
     * IS_SORTED(this, [relation computed by order.compare method])
     * </pre>
     */
    public void sort(Comparator<T> order) {
        if (this.length() > 1) {
            /*
             * Dequeue the partitioning entry from this
             */

            T curr = this.dequeue();

            /*
             * Partition this into two queues as discussed above (you will need
             * to declare and initialize two new queues)
             */
            Queue<T> front = this.newInstance();
            Queue<T> back = this.newInstance();

            partition(this, curr, front, back, order);

            /*
             * Recursively sort the two queues
             */

            front.sort(order);
            back.sort(order);

            /*
             * Reconstruct this by combining the two sorted queues and the
             * partitioning entry in the proper order
             */

            front.append(curr);
            front.append(back);
            this.transferFrom(front);

        }
    }

    /**
     * @mathdefinitions <pre>
     * IS_TOTAL_PREORDER (
     *   r: binary relation on T
     *  ) : boolean is
     *  for all x, y, z: T
     *   ((r(x, y) or r(y, x))  and
     *    (if (r(x, y) and r(y, z)) then r(x, z)))
     *
     * IS_SORTED (
     *   s: string of T,
     *   r: binary relation on T
     *  ) : boolean is
     *  for all x, y: T where (<x, y> is substring of s) (r(x, y))
     * </pre>
     */

}
