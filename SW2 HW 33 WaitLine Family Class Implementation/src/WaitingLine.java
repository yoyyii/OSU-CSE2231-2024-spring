/**
 * WaitingLine enhance Interface
 *
 * @author Yoyi, Sam, Debjani
 *
 */

public interface WaitingLine<T> extends WaitingLineKernel<T> {

    /**
     * get the position in {@code this} with given entry.
     *
     * @param x
     *            the entry that wish to get the position
     * @aliases reference {@code x}
     * @requires this \= <> and and x is in {@code this}
     * @ensures getPosition = position of x in {@code this}
     */
    int getPosition(T x);

    /**
     * get the entry in {@code this} with given position.
     *
     * @param pos
     *            the position that wish to get the entry
     * @requires this \= <> and 0 < {@code pos} < this.length
     * @ensures getPosition = position of x in {@code this}
     */
    T getEntry(int pos);

    /*
     * Reports the front of {@code this}
     *
     * @return the front entry of {@code this}
     *
     * @aliases reference returned by {@code this}
     *
     * @requires {@code this \= <>}
     *
     * @ensures {@code <front> is prefix of this}
     */
    T front();

    /**
     * Removes and returns the entry at the given position {@code this}.
     *
     * @param pos
     *            the position of the entry we want to remove
     * @return the entry removed
     * @updates this
     * @requires {@code this /= <>}
     * @ensures {@code this = #this * <removeFromPosition> * #this}
     */
    T removeFromPosition(int pos);

}