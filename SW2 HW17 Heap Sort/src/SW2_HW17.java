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
public final class SW2_HW17 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW17() {
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
     * Checks if the given {@code BinaryTree<Integer>} satisfies the heap
     * ordering property according to the <= relation.
     *
     * @param t
     *            the binary tree
     * @return true if the given tree satisfies the heap ordering property;
     *         false otherwise
     * @ensures <pre>
     * satisfiesHeapOrdering = [t satisfies the heap ordering property]
     * </pre>
     */
    private static boolean satisfiesHeapOrdering(BinaryTree<Integer> t) {
        BinaryTree<Integer> left = t.newInstance();
        BinaryTree<Integer> right = t.newInstance();
        boolean satisfy = true;

        if (t.size() > 0) {

            Integer root = t.disassemble(left, right);

            if (root > left.root() || root > right.root()) {
                satisfy = false;
            } else {
                satisfy = satisfiesHeapOrdering(left)
                        && satisfiesHeapOrdering(right);
            }

            t.assemble(root, left, right);

        }

        return satisfy;
    }

}
