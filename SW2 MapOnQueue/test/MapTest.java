import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size

    /*
     * test constructor
     */

    @Test
    public void testConstructor1() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> constructor = this.constructorTest();
        Map<String, String> constructorExpected = this.constructorRef();

        assertEquals(constructor, constructorExpected);
    }

    /*
     * test add
     */

    @Test
    //add a string to a non-empty map
    public void testAdd1() {

        Map<String, String> m1 = this.createFromArgsTest("a", "aKey", "b",
                "bKey");
        Map<String, String> m1Expected = this.createFromArgsRef("a", "aKey",
                "b", "bKey", "c", "cKey");
        m1.add("c", "cKey");

        assertEquals(m1, m1Expected);
    }

    @Test
    //add a string to an empty map
    public void testAdd2() {

        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m1Expected = this.createFromArgsRef("c", "cKey");
        m1.add("c", "cKey");

        assertEquals(m1, m1Expected);
    }

    /*
     * test remove
     */

    @Test
    //remove a pair from an non-empty map
    public void testRemove1() {

        Map<String, String> m1 = this.createFromArgsTest("a", "aKey", "b",
                "bKey");
        Map<String, String> m1Expected = this.createFromArgsRef("a", "aKey");
        m1.remove("b");

        assertEquals(m1, m1Expected);
    }

    @Test
    //remove a pair from a map with a size of 1
    public void testRemove2() {

        Map<String, String> m1 = this.createFromArgsTest("b", "bKey");
        Map<String, String> m1Expected = this.createFromArgsRef();
        m1.remove("b");

        assertEquals(m1, m1Expected);
    }

    /*
     * test removeAny
     */

    @Test
    //remove any pair from an non-empty map
    public void testRemoveAny1() {

        Map<String, String> m1 = this.createFromArgsTest("a", "aKey", "b",
                "bKey");
        Map<String, String> m1Expected = this.createFromArgsRef("a", "aKey",
                "b", "bKey");
        Map.Pair<String, String> remove = m1.removeAny();

        assertEquals(m1Expected.hasKey(remove.key()), true);
        assertEquals(m1.size(), m1Expected.size() - 1);

    }

    @Test
    //remove any string from a set of size 1
    public void testRemoveAny2() {

        Map<String, String> m1 = this.createFromArgsTest("a", "aKey");
        Map<String, String> m1Expected = this.createFromArgsRef("a", "aKey");
        Map.Pair<String, String> remove = m1.removeAny();

        assertEquals(m1Expected.hasKey(remove.key()), true);
        assertEquals(m1.size(), m1Expected.size() - 1);
    }

    /*
     * test value
     */

    @Test
    //pass in a key that has an associate value in the map
    public void testValue1() {

        Map<String, String> m1 = this.createFromArgsTest("a", "aKey", "b",
                "bKey");
        Map<String, String> m1Expected = this.createFromArgsRef("a", "aKey",
                "b", "bKey");
        String target = "a";

        assertEquals(m1.value(target), "aKey");
        assertEquals(m1Expected.value(target), "aKey");
    }

    /*
     * test hasKey
     */

    @Test
    //pass in a target that is contained in the map
    public void testContains1() {

        Map<String, String> m1 = this.createFromArgsTest("a", "aKey");
        Map<String, String> m1Expected = this.createFromArgsRef("a", "aKey");
        String target = "a";

        assertEquals(m1.hasKey(target), true);
        assertEquals(m1Expected.hasKey(target), true);
    }

    @Test
    //pass in a target that is not contained in the map
    public void testContains2() {

        Map<String, String> m1 = this.createFromArgsTest("a", "aKey");
        Map<String, String> m1Expected = this.createFromArgsRef("a", "aKey");
        String target = "c";

        assertEquals(m1.hasKey(target), false);
        assertEquals(m1Expected.hasKey(target), false);
    }

    /*
     * test size
     */

    @Test
    //size of a non-empty map
    public void testSize1() {

        Map<String, String> m1 = this.createFromArgsTest("a", "aKey", "b",
                "bKey");
        Map<String, String> m1Expected = this.createFromArgsRef("a", "aKey",
                "b", "bKey");

        assertEquals(m1.size(), 2);
        assertEquals(m1Expected.size(), 2);
    }

    @Test
    //size of an empty map
    public void testSize2() {

        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m1Expected = this.createFromArgsRef();

        assertEquals(m1.size(), 0);
        assertEquals(m1Expected.size(), 0);
    }

}
