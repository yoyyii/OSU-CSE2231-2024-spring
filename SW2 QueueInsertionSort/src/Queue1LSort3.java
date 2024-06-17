import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 *
 * @param <T>
 *            type of {@code Queue} entries
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
public final class Queue1LSort3<T> extends Queue1L<T> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort3() {
        super();
    }

    /**
     * Inserts the given {@code T} in the {@code Queue<T>} sorted according to
     * the given {@code Comparator<T>} and maintains the {@code Queue<T>}
     * sorted.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to insert into
     * @param x
     *            the {@code T} to insert
     * @param order
     *            the {@code Comparator} defining the order for {@code T}
     * @updates q
     * @requires <pre>
     * IS_TOTAL_PREORDER([relation computed by order.compare method])  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     * @ensures <pre>
     * perms(q, #q * <x>)  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     */
    private static <T> void insertInOrder(Queue<T> q, T x,
            Comparator<T> order) {
        assert q != null : "Violation of: q is not null";
        assert x != null : "Violation of: x is not null";
        assert order != null : "Violation of: order is not null";

        // TODO - fill in body
        boolean flag = false;
        Queue<T> left = q.newInstance();
        Queue<T> right = q.newInstance();

        for (int i = q.length(); i > 0 && !flag; i--) {
            T curr = q.dequeue();
            if (order.compare(curr, x) < 0) {
                left.enqueue(curr);
            } else {
                left.enqueue(x);
                right.enqueue(curr);
                flag = true;
            }
        }

        if (!flag) {
            left.enqueue(x);
        }
        left.append(right);
        left.append(q);
        q.transferFrom(left);
    }

    @Override
    public void sort(Comparator<T> order) {
        assert order != null : "Violation of: order is not null";

        // TODO - fill in body
        Queue<T> newQueue = this.newInstance();

        for (int i = this.length(); i > 0; i--) {
            insertInOrder(newQueue, this.dequeue(), order);
        }
        this.transferFrom(newQueue);

    }

}