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
public final class SW2_HW14 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW14() {
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
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        boolean flag = false;
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        T curr = t.disassemble(left, right);

        if (t.height() >= 1) {
            if (curr.compareTo(x) == 0) {
                flag = true;
            } else {
                flag = isInTree(left, x);

                if (!flag) {
                    flag = isInTree(right, x);
                }

                t.assemble(curr, left, right);

            }
        }

        return flag;
    }

}
