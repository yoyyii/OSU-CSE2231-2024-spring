import org.w3c.dom.Node;

public class SW2_HW19 {

    /**
     * Retreats the position in {@code this} by one.
     *
     * @updates this
     * @requires this.left /= <>
     * @ensures <pre>
     * this.left * this.right = #this.left * #this.right  and
     * |this.left| = |#this.left| - 1
     * </pre>
     */
    public void retreat() {

        Node smart = this.preFront;

        for (int i = 0; i < leftLength(); i++) {
            smart = smart.next();
        }

        this.leftLength--;
        this.rightLength++;
    }

}
