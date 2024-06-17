package components.waitingline;

import java.util.Iterator;

import components.set.Set;

public abstract class WaitingLineSecondary<T> implements WaitingLine<T> {

    /*
     * Private members --------------------------------------------------------
     */

    private Set<T> inLineAlready;

    private int length;

    /**
     * Node class for singly linked list nodes.
     */
    private final class Node {

        /**
         * Data in node.
         */
        private T entry;

        /**
         * Next node in singly linked list, or null.
         */
        private Node next;

        private Node prev;

    }

    private Node preFront;
    private Node rear;

    /*
     * Public members ---------------------------------------------------------
     */

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.preFront = new Node();

        //created a new node for postFinish
        this.rear = new Node();
        //pointed preStart.next to postFinish
        this.preFront.next = this.rear;
        this.rear.prev = this.preFront;

        this.length = 0;

    }

    /**
     * No-argument constructor.
     */
    public WaitingLineSecondary() {
        this.createNewRep();
    }

    /*
     * Common methods (from Object) -------------------------------------------
     */

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WaitingLine<?>)) {
            return false;
        }
        WaitingLine<?> q = (WaitingLine<?>) obj;
        if (this.length() != q.length()) {
            return false;
        }
        Iterator<T> it1 = this.iterator();
        Iterator<?> it2 = q.iterator();
        while (it1.hasNext()) {
            T x1 = it1.next();
            Object x2 = it2.next();
            if (!x1.equals(x2)) {
                return false;
            }
        }
        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        final int samples = 2;
        final int a = 37;
        final int b = 17;
        int result = 0;
        /*
         * This code makes hashCode run in O(1) time. It works because of the
         * iterator order string specification, which guarantees that the (at
         * most) samples entries returned by the it.next() calls are the same
         * when the two Queues are equal.
         */
        int n = 0;
        Iterator<T> it = this.iterator();
        while (n < samples && it.hasNext()) {
            n++;
            T x = it.next();
            result = a * result + b * x.hashCode();
        }
        return result;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(",");
            }
        }
        result.append(">");
        return result.toString();
    }

    /*
     * Other non-kernel methods -----------------------------------------------
     */
    /**
     * get the position in {@code this} with given entry.
     *
     * @param x
     *            the entry that wish to get the position
     * @aliases reference {@code x}
     * @requires this \= <> and and x is in {@code this}
     * @ensures getPosition = position of x in {@code this}
     */
    @Override
    public int getPosition(T x) {
        int pos;
        boolean found = false;
        Node p = new Node();
        p = this.preFront.next;

        for (pos = 0; pos < this.length; pos++) {
            if (p.entry.equals(x)) {
                found = true;
            }
        }

        return pos;
    }

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
    @Override
    public void addToLine(T x) {
        Node p = new Node();
        p.entry = x;
        p.next = this.rear;
        p.prev = this.rear.prev;
        this.rear.prev.next = p;
        p.next.prev = p;

        this.length++;
        this.inLineAlready.add(x);

    }

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
    @Override
    public T removeFromPosition(int pos) {

        Node p = new Node();
        p = this.preFront.next;
        T entry;
        for (int i = 0; i < pos; i++) {
            p = p.next;
        }
        p.prev.next = p.next;
        p.next.prev = p.prev;

        entry = p.entry;

        this.length--;
        this.inLineAlready.remove(entry);

        return entry;

    }

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    @Override
    public int length() {
        return this.length;
    }

    /*
     * Reports whether x is in {@code this} already
     *
     * @param x entry want to check
     *
     * @return whether {@code x} is in {@code this} or not
     *
     */
    @Override
    public boolean inLineAlready(T entry) {
        return this.inLineAlready.contains(entry);
    }
}