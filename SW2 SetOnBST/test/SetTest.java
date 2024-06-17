import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Yoyi Liao, Yutong Ye
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    /*
     * test constructor
     */

    @Test
    // test empty constructor
    public final void constructor() {
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();

        assertEquals(sExpected, s);
    }

    /*
     * test add
     */

    @Test
    //add an element to the empty set
    public void testAdd1() {
        Set<String> s = this.createFromArgsRef();
        Set<String> sExpected = this.createFromArgsTest("a");

        s.add("a");

        assertEquals(sExpected, s);
    }

    @Test
    //add an element to a non-empty set
    public void testAdd2() {
        Set<String> s = this.createFromArgsRef("a", "b", "c");
        Set<String> sExpected = this.createFromArgsTest("a", "b", "c", "d");

        s.add("d");

        assertEquals(sExpected, s);
    }

    /*
     * test remove
     */

    @Test
    //remove an element from a set contains only one element
    public void testRemove1() {
        Set<String> s = this.createFromArgsRef("a");
        Set<String> sExpected = this.createFromArgsTest();

        String hold = s.remove("a");

        assertEquals(sExpected, s);
        assertEquals("a", hold);
    }

    @Test
    //remove an element from a set contains more than one element
    public void testRemove2() {
        Set<String> s = this.createFromArgsRef("a", "b", "c");
        Set<String> sExpected = this.createFromArgsTest("a", "c");

        String hold = s.remove("b");

        assertEquals(sExpected, s);
        assertEquals("b", hold);
    }

    /*
     * test removeAny
     */

    @Test
    //remove any string from an non-empty set
    public void testRemoveAny1() {

        Set<String> s1 = this.createFromArgsRef("a", "b");
        Set<String> s1Expected = this.createFromArgsTest("a", "b");
        String remove = s1.removeAny();

        boolean contain = s1Expected.contains(remove);

        assertEquals(contain, true);
        assertEquals(s1Expected.size() - 1, s1.size());
    }

    @Test
    //remove any string from a set of size 1
    public void testRemoveAny2() {

        Set<String> s1 = this.createFromArgsRef("a");
        Set<String> s1Expected = this.createFromArgsTest("a");
        String remove = s1.removeAny();

        boolean contain = s1Expected.contains(remove);

        assertEquals(contain, true);
        assertEquals(s1Expected.size() - 1, s1.size());
    }

    /*
     * test contains
     */

    @Test
    //pass in a target that is contained in the set
    public void testContains1() {

        Set<String> s1 = this.createFromArgsRef("a", "b");
        Set<String> s1Expected = this.createFromArgsTest("a", "b");
        String target = "a";

        assertEquals(s1.contains(target), true);
        assertEquals(s1Expected, s1);
    }

    @Test
    //pass in a target that is not contained in the set
    public void testContains2() {

        Set<String> s1 = this.createFromArgsRef("a", "b");
        Set<String> s1Expected = this.createFromArgsTest("a", "b");
        String target = "c";

        assertEquals(s1.contains(target), false);
        assertEquals(s1Expected, s1);
    }

    /*
     * test size
     */

    @Test
    //size of a non-empty set
    public void testSize1() {

        Set<String> s1 = this.createFromArgsRef("a", "b");
        Set<String> s1Expected = this.createFromArgsTest("a", "b");

        int s1Size = s1.size();

        assertEquals(s1Size, 2);
        assertEquals(s1Expected, s1);
    }

    @Test
    //size of an empty set
    public void testSize2() {

        Set<String> s1 = this.createFromArgsRef();
        Set<String> s1Expected = this.createFromArgsTest();

        int s1Size = s1.size();

        assertEquals(s1Size, 0);
        assertEquals(s1Expected, s1);
    }

}
