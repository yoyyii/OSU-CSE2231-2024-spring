import java.util.Iterator;

import components.sequence.Sequence;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.tree.Tree;

/**
 * Put a short phrase describing the program here.
 *
 * @author Yoyi Liao
 *
 */
public final class SW2_HW21 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW21() {
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
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int size(Tree<T> t) {

        int size = 0;

        Sequence<Tree<T>> sub = t.newSequenceOfTree();

        if (t.height() != 0) {
            T root = t.disassemble(sub);
            for (int i = sub.length(); i > 0; i--) {
                size += size(sub.entry(i));
            }

            t.assemble(root, sub);
        }

        return size;
    }

    public static <T> int sizeIterator(Tree<T> t) {

        int size = 0;

        Iterator<T> itr = t.iterator();

        while (itr.hasNext()) {
            size++;
        }

        return size;
    }

    /**
     * Returns the height of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose height to return
     * @return the height of the given {@code Tree}
     * @ensures height = ht(t)
     */
    public static <T> int height(Tree<T> t) {

        int height = 0;

        Sequence<Tree<T>> subTree = t.newSequenceOfTree();

        if (t.size() != 0) {
            height = 1;
            T root = t.disassemble(subTree);

            for (int i = subTree.length(); i > 0; i--) {
                height += height(subTree.entry(i));
            }

            t.assemble(root, subTree);
        }

        return height;

    }

    /**
     * Returns the largest integer in the given {@code Tree<Integer>}.
     *
     * @param t
     *            the {@code Tree<Integer>} whose largest integer to return
     * @return the largest integer in the given {@code Tree<Integer>}
     * @requires |t| > 0
     * @ensures <pre>
     * max is in labels(t)  and
     * for all i: integer where (i is in labels(t)) (i <= max)
     * </pre>
     */
    public static int max(Tree<Integer> t) {

        Sequence<Tree<Integer>> subTree = t.newSequenceOfTree();

        int max = t.root();

        if (t.height() > 1) {
            int root = t.disassemble(subTree);
            for (int i = 0; i < subTree.length()
                    && subTree.entry(i).height() > 0; i++) {
                max = Math.max(max, max(subTree.entry(i)));
            }
            t.assemble(root, subTree);
        }

        return max;

    }

}
