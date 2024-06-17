import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Yoyi Liao, Yutong Ye
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

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    /*
     * test constructor
     */

    @Test
    public void testConstructor() {
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
        assertEquals(sExpected, s);
    }

    /*
     * test add
     */

    @Test
    public void testAdd1() {
        Map<String, String> s = this.createFromArgsTest("A", "a", "B", "b", "C",
                "c");
        Map<String, String> sExpected = this.createFromArgsTest("A", "a", "B",
                "b", "C", "c", "D", "d");
        s.add("D", "d");
        assertEquals(s, sExpected);
    }

    @Test
    public void testAdd2() {
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsTest("A", "a");
        s.add("A", "a");
        assertEquals(s, sExpected);
    }

    /*
     * test remove
     */

    @Test
    public void testRemove1() {
        Map<String, String> s = this.createFromArgsTest("A", "a", "B", "b", "C",
                "c");
        Map<String, String> sExpected = this.createFromArgsRef("A", "a", "B",
                "b");

        s.remove("C");

        assertEquals(sExpected, s);
    }

    @Test
    public void testRemove2() {
        Map<String, String> s = this.createFromArgsTest("A", "a");
        Map<String, String> sExpected = this.createFromArgsRef();

        s.remove("A");

        assertEquals(sExpected, s);
    }

    /*
     * test removeAny
     */

    @Test
    public void testRemoveAny1() {
        Map<String, String> s = this.createFromArgsTest("A", "a", "B", "b", "C",
                "c");
        Map<String, String> sExpected = this.createFromArgsRef("A", "a", "B",
                "b", "C", "c");
        Map.Pair<String, String> temp1 = s.removeAny();
        Map.Pair<String, String> temp2 = sExpected.remove(temp1.key());

        assertEquals(s, sExpected);
        assertEquals(temp2, temp1);
    }

    public void testRemoveAny2() {
        Map<String, String> s = this.createFromArgsTest("A", "a");
        Map<String, String> sExpected = this.createFromArgsRef("A", "a");
        Map.Pair<String, String> temp1 = s.removeAny();
        Map.Pair<String, String> temp2 = sExpected.remove(temp1.key());

        assertEquals(s, sExpected);
        assertEquals(temp2, temp1);
    }

    /*
     * test size
     */

    @Test
    public void testSize1() {
        Map<String, String> s = this.createFromArgsTest("A", "a", "B", "b", "C",
                "c");
        int a = s.size();
        int aExpected = 3;
        assertEquals(aExpected, a);
    }

    @Test
    public void testSize2() {
        Map<String, String> s = this.createFromArgsTest();
        int a = s.size();
        int aExpected = 0;
        assertEquals(aExpected, a);
    }

    /*
     * test Value
     */

    @Test
    public void testValue() {
        Map<String, String> s = this.createFromArgsTest("A", "a", "B", "b", "C",
                "c");
        String value = s.value("B");
        String valueExpected = "b";
        assertEquals(valueExpected, value);
    }

    /*
     * test hasKey
     */

    @Test
    public void testHasKey1() {
        Map<String, String> s = this.createFromArgsTest("A", "a", "B", "b", "C",
                "c");
        boolean a = s.hasKey("B");
        assertEquals(true, a);
    }

    @Test
    public void testHasKey2() {
        Map<String, String> s = this.createFromArgsTest("A", "a", "B", "b", "C",
                "c");
        boolean a = s.hasKey("D");
        assertEquals(false, a);
    }

}
