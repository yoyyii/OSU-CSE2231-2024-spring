import components.queue.Queue;
import components.standard.Standard;

/**
 * WaitingLine Kernel Interface
 *
 * @author Yoyi, Sam, Debjani
 *
 */

public interface WaitingLineKernel<T> extends Standard<Queue<T>>, Iterable<T> {
    /**
     * Adds {@code x} to the end of {@code this}.
     *
     * @param x
     *            the entry to be added
     * @aliases reference {@code x}
     * @updates this
     * @requires x is not in {@code this}
     * @ensures {@code this = #this * <x>}
     */
    void addToLine(T x);

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();

    /*
     * Reports whether x is in {@code this} already
     *
     * @param x entry want to check
     *
     * @return whether {@code x} is in {@code this} or not
     *
     */
    boolean contain(T x);

    /*
     * remove {@code x} from {@code this}.
     *
     * @param x the entry to be remove
     *
     * @updates this
     *
     * @requires x is in {@code this}
     *
     * @ensures {@code this = #this * \ {x}
     */
    T removeFromLine(T x);

}