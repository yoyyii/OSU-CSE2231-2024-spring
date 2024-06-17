package components.waitingline;

import components.standard.Standard;

public interface WaitingLineKernel<T>
        extends Standard<WaitingLine<T>>, Iterable<T> {
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
    boolean inLineAlready(T x);

}