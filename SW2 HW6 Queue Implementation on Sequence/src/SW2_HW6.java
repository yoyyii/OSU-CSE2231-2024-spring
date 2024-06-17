import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class SW2_HW6 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW6() {
    }

    /**
     * Reports the front of {@code this}.
     *
     * @param <T>
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    @Override
    public T front() {
        assert this.length() > 0 : "Violation of: this /= <>";

        // fill in body

        int length = this.length() - 1;
        T front = this.dequeue();
        this.enqueue(front);

        for (int j = 0; j < length; j++) {
            T temp = this.dequeue();
            this.enqueue(temp);
        }

        return front;

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

}
