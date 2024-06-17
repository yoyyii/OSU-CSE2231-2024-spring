package components.waitingline;
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

    T front();

}