import components.binarytree.BinaryTree;
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
public final class SW2_HW12 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW12() {
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
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size(BinaryTree<T> t) {
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        int count = 0;

        if (t.height() == 0) {
            count = 1;
        } else {
            T curr = t.disassemble(left, right);
            count += size(left);
            count += size(right);
            t.assemble(curr, left, right);
        }

        return count;
    }

    // size using for loop
    public static <T> int sizeIrretative(BinaryTree<T> t) {
        int size = 0;
        for (T x : t) {
            size++;
        }

        return size;
    }

}
