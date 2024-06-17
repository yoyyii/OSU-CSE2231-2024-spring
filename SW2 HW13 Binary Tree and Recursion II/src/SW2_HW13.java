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
public final class SW2_HW13 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW13() {
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

    /*
     * Returns the {@code String} prefix representation of the given {@code
     * BinaryTree<T>}.
     *
     * @param <T> the type of the {@code BinaryTree} node labels
     *
     * @param t the {@code BinaryTree} to convert to a {@code String}
     *
     * @return the prefix representation of {@code t}
     *
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        String string = "";

        if (t.height() == 0) {

            string += "()";

        } else {

            T curr = t.disassemble(left, right);
            string += (t.root().toString() + "(" + treeToString(left)
                    + treeToString(right) + ")");

            t.assemble(curr, left, right);
        }

        return string;

    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {
        BinaryTree<Integer> left = t.newInstance();
        BinaryTree<Integer> right = t.newInstance();
        BinaryTree<Integer> copy = t.newInstance();

        if (t.height() != 0) {

            int curr = t.disassemble(left, right);
            copy.assemble(curr, copy(left), copy(right));
            t.assemble(curr, left, right);
        }

        return copy;
    }

}
