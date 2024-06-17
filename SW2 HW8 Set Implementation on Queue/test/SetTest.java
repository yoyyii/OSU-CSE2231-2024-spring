import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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

    /**
     * Test constructor method.
     */

    @Test
    public void testConstructor1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> constructor = this.constructorTest();
        Set<String> constructorExpected = this.constructorRef();

        assertEquals(constructor, constructorExpected);
    }

    /**
     * Test add method.
     */

    @Test
    //add a string to a non-empty set
    public void testAdd1() {

        Set<String> s1 = this.createFromArgsTest("a", "b", "c");
        Set<String> s1Expected = this.createFromArgsRef("a", "b", "c", "z");
        s1.add("z");

        assertEquals(s1, s1Expected);
    }

    @Test
    //add a string to an empty set
    public void testAdd2() {

        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsRef("a");
        s1.add("a");

        assertEquals(s1, s1Expected);
    }

    /*
     * test remove method
     */

    @Test
    //remove a string from an non-empty set
    public void testRemove1() {

        Set<String> s1 = this.createFromArgsTest("a", "b");
        Set<String> s1Expected = this.createFromArgsRef("a");
        s1.remove("b");

        assertEquals(s1, s1Expected);
    }

    @Test
    //remove a string from a set with a size of 1
    public void testRemove2() {

        Set<String> s1 = this.createFromArgsTest("a");
        Set<String> s1Expected = this.createFromArgsRef();
        s1.remove("a");

        assertEquals(s1, s1Expected);
    }

    /*
     * test method for removeAny
     */

    @Test
    //remove any string from an non-empty set
    public void testRemoveAny1() {

        Set<String> s1 = this.createFromArgsTest("a", "b");
        Set<String> s1Expected = this.createFromArgsTest("a", "b");
        String remove = s1.removeAny();

        boolean contain = s1Expected.contains(remove);

        assertEquals(contain, true);
        assertEquals(s1.size(), s1Expected.size() - 1);
    }

    @Test
    //remove any string from a set of size 1
    public void testRemoveAny2() {

        Set<String> s1 = this.createFromArgsTest("a");
        Set<String> s1Expected = this.createFromArgsTest("a");
        String remove = s1.removeAny();

        boolean contain = s1Expected.contains(remove);

        assertEquals(contain, true);
        assertEquals(s1.size(), s1Expected.size() - 1);
    }

    /*
     * test method for contains
     */

    @Test
    //pass in a target that is contained in the set
    public void testContains1() {

        Set<String> s1 = this.createFromArgsTest("a", "b");
        Set<String> s1Expected = this.createFromArgsTest("a", "b");
        String target = "a";

        assertEquals(s1.contains(target), true);
        assertEquals(s1Expected.contains(target), true);
    }

    @Test
    //pass in a target that is not contained in the set
    public void testContains2() {

        Set<String> s1 = this.createFromArgsTest("a", "b");
        Set<String> s1Expected = this.createFromArgsTest("a", "b");
        String target = "c";

        assertEquals(s1.contains(target), false);
        assertEquals(s1Expected.contains(target), false);
    }

    /*
     * test method for size
     */

    @Test
    //size of a non-empty set
    public void testSize1() {

        Set<String> s1 = this.createFromArgsTest("a", "b");
        Set<String> s1Expected = this.createFromArgsTest("a", "b");

        assertEquals(s1.size(), 2);
        assertEquals(s1Expected.size(), 2);
    }

    @Test
    //size of an empty set
    public void testSize2() {

        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsTest();

        assertEquals(s1.size(), 0);
        assertEquals(s1Expected.size(), 0);
    }

}