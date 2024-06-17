import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

/**
 * JUnit test fixture for {@code BinarySearchTreeMethods}'s static methods
 * isInTree (and removeSmallest).
 */
public final class BinarySearchTreeMethodsTest {

    /**
     * Constructs and return a BST created by inserting the given {@code args}
     * into an empty tree in the order in which they are provided.
     *
     * @param args
     *            the {@code String}s to be inserted in the tree
     * @return the BST with the given {@code String}s
     * @requires [the Strings in args are all distinct]
     * @ensures createBSTFromArgs = [the BST with the given Strings]
     */
    private static BinaryTree<String> createBSTFromArgs(String... args) {
        BinaryTree<String> t = new BinaryTree1<String>();
        for (String s : args) {
            BinaryTreeUtility.insertInTree(t, s);
        }
        return t;
    }

    @Test
    public void sampleTest() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    // TODO: add here other test cases for BinarySearchTreeMethods.isInTree
    // (and for BinarySearchTreeMethods.removeSmallest)

    /*
     * test isInTree
     */

    @Test
    // target not in tree
    public void testIsInTree1() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, inTree);
        assertEquals(t2, t1);
    }

    @Test
    // target in second layer of tree
    public void testIsInTree2() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c", "d");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c", "d");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    /*
     * test removeSmallest
     */

    @Test
    // smallest is the left most node
    public void testRemoveSmallest1() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "c");
        /*
         * Call method under test
         */
        String removeSmallest = BinarySearchTreeMethods.removeSmallest(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("a", removeSmallest);
        assertEquals(t2, t1);
    }

    @Test
    // smallest is the root
    public void testRemoveSmallest2() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("a", "b", "c");
        BinaryTree<String> t2 = createBSTFromArgs("c", "b");
        /*
         * Call method under test
         */
        String removeSmallest = BinarySearchTreeMethods.removeSmallest(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("a", removeSmallest);
        assertEquals(t2, t1);
    }

    @Test
    // smallest has a right child
    public void testRemoveSmallest3() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("e", "a", "b", "f");
        BinaryTree<String> t2 = createBSTFromArgs("e", "b", "f");
        /*
         * Call method under test
         */
        String removeSmallest = BinarySearchTreeMethods.removeSmallest(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("a", removeSmallest);
        assertEquals(t2, t1);
    }

}
