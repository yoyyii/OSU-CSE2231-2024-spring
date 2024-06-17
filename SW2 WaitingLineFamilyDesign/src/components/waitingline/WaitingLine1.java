package components.waitingline;

import java.util.Iterator;
import java.util.NoSuchElementException;

import components.set.Set;

public class WaitingLine1<T> extends WaitingLineSecondary<T> {
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
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final WaitingLine<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(WaitingLine<T> source) {
        assert source instanceof WaitingLine1<?> : ""
                + "Violation of: source is of dynamic type List3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type List3<?>, and
         * the ? must be T or the call would not have compiled.
         */
        WaitingLine1<T> localSource = (WaitingLine1<T>) source;
        this.preFront = localSource.preFront;
        this.rear = localSource.rear;
        localSource.createNewRep();

    }

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
    public WaitingLine1() {
        this.createNewRep();
    }

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
        Node p = new Node();
        p = this.preFront.next;
        int pos = 0;
        while (p.next != this.rear && !p.entry.equals(x)) {
            pos++;
        }
        return pos;
    }

    /**
     * get the entry in {@code this} with given position.
     *
     * @param pos
     *            the position that wish to get the entry
     * @requires this \= <> and 0 < {@code pos} < this.length
     * @ensures getPosition = position of x in {@code this}
     */
    @Override
    public T getEntry(int pos) {

        Node p = new Node();
        p = this.preFront.next;
        for (int i = 0; i < pos; i++) {
            p = p.next;
        }
        return p.entry;
    }

    @Override
    public T front() {
        return this.preFront.next.entry;
    }

    /**
     * Implementation of {@code Iterator} interface for {@code List3}.
     */
    private final class WaitingLineIterator implements Iterator<T> {

        /**
         * Current node in the linked list.
         */
        private Node current;

        /**
         * No-argument constructor.
         */
        private WaitingLineIterator() {
            this.current = WaitingLine1.this.preFront.next;
        }

        @Override
        public boolean hasNext() {
            return this.current != WaitingLine1.this.rear;
        }

        @Override
        public T next() {
            assert this.hasNext() : "Violation of: ~this.unseen /= <>";
            if (!this.hasNext()) {
                /*
                 * Exception is supposed to be thrown in this case, but with
                 * assertion-checking enabled it cannot happen because of assert
                 * above.
                 */
                throw new NoSuchElementException();
            }
            T x = this.current.entry;
            this.current = this.current.next;
            return x;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }

    }

}