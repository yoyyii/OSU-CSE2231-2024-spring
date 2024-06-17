import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Yoyi Liao, Yutong Ye
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    @Test
    //adding elements into an empty sorting machine
    public final void testAdd1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");
        m.add("a");
        m.add("b");
        m.add("c");
        assertEquals(mExpected, m);
    }

    @Test
    //adding elements into an non empty sorting machine
    public final void testAdd2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");

        m.add("b");
        m.add("c");
        assertEquals(mExpected, m);
    }

    /*
     * test changeToExtractionMode
     */

    @Test
    // change to extraction mode
    public final void testChangeToExtractionMode1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        m.changeToExtractionMode();
        assertEquals(false, m.isInInsertionMode());
    }

    @Test
    // do not change to extraction mode
    public final void testChangeToExtractionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        assertEquals(true, m.isInInsertionMode());
    }

    @Test
    // do not change to extraction mode when there's element in sortingMachine
    public final void testChangeToExtractionMode3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b");

        assertEquals(true, m.isInInsertionMode());
    }

    /*
     * test removeFirst
     */

    @Test
    //remove first element from a sorting machine that has more than 1 elements
    public final void testRemoveFirst1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "c",
                "b", "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "b", "c");

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String first = m.removeFirst();

        assertEquals(mExpected, m);
        assertEquals("a", first);
    }

    @Test
    //remove first element from a sorting machine that has only 1 element
    public final void testRemoveFirst2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        String first = m.removeFirst();

        assertEquals(mExpected, m);
        assertEquals("c", first);
    }

    /*
     * test isInInsertionMode
     */

    @Test
    // is in insertion mode
    public final void testIsInInsertionMode1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        assertEquals(true, m.isInInsertionMode());
    }

    @Test
    // not in insertion mode
    public final void testIsInInsertionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        assertEquals(false, m.isInInsertionMode());
    }

    /*
     * test order
     */

    @Test
    // order is true
    public final void testOrder1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected.order(), m.order());
    }

    @Test
    // order is false
    public final void testOrder2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        assertEquals(mExpected.order(), m.order());
    }

    /*
     * test size
     */

    @Test
    // size of a sorting machine that has no element
    public final void testSize1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        assertEquals(0, m.size());
    }

    @Test
    // size of a sorting machine that has more than 1 elements
    public final void testSize2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "c",
                "b", "a");
        assertEquals(3, m.size());
    }

}
