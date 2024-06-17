import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length

    /**
     * Test constructor method.
     */

    @Test
    public void testConstructor1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> constructor = this.constructorTest();
        Sequence<String> constructorExpected = this.constructorRef();

        assertEquals(constructor, constructorExpected);
    }

    /**
     * Test add method.
     */

    @Test
    //add a string to the front of the sequence
    public void testAdd1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest("a", "b", "c");
        Sequence<String> s1Expected = this.createFromArgsRef("z", "a", "b",
                "c");
        s1.add(0, "z");

        assertEquals(s1, s1Expected);
    }

    @Test
    //add a string to the back of the sequence
    public void testAdd2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest("a", "b", "c");
        Sequence<String> s1Expected = this.createFromArgsRef("a", "b", "c",
                "z");
        s1.add(s1.length(), "z");

        assertEquals(s1, s1Expected);
    }

    @Test
    //add a string to the front of the sequence
    public void testAdd3() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest("a", "b", "c");
        Sequence<String> s1Expected = this.createFromArgsRef("a", "b", "z",
                "c");
        s1.add(2, "z");

        assertEquals(s1, s1Expected);
    }

    @Test
    //add a string to an empty sequence
    public void testAdd4() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest();
        Sequence<String> s1Expected = this.createFromArgsRef("z");
        s1.add(0, "z");

        assertEquals(s1, s1Expected);
    }

    /**
     * Test remove method.
     */

    @Test
    //remove a string at the end of the sequence
    public void testRemove1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest("a", "b", "c");
        Sequence<String> s1Expected = this.createFromArgsRef("a", "b");
        String remove = s1.remove(s1.length());
        String removeExpected = "c";

        assertEquals(s1, s1Expected);
        assertEquals(remove, removeExpected);

    }

    @Test
    //remove a string at the front of the sequence
    public void testRemove2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest("a", "b", "c");
        Sequence<String> s1Expected = this.createFromArgsRef("b", "c");
        String remove = s1.remove(0);
        String removeExpected = "a";

        assertEquals(s1, s1Expected);
        assertEquals(remove, removeExpected);

    }

    @Test
    //remove a string in the middle of the sequence
    public void testRemove3() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest("a", "b", "c");
        Sequence<String> s1Expected = this.createFromArgsRef("a", "c");
        String remove = s1.remove(1);
        String removeExpected = "b";

        assertEquals(s1, s1Expected);
        assertEquals(remove, removeExpected);

    }

    @Test
    //remove the only string in the sequence
    public void testRemove4() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest("a");
        Sequence<String> s1Expected = this.createFromArgsRef();
        String remove = s1.remove(0);
        String removeExpected = "a";

        assertEquals(s1, s1Expected);
        assertEquals(remove, removeExpected);

    }

    /**
     * Test length method.
     */

    @Test
    //test if it return the length of an non-empty Sequence
    public void testLength1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest("a", "b", "c");

        int l1 = s1.length();
        int l1Expected = 3;

        assertEquals(l1, l1Expected);

    }

    @Test
    //test if it return the length of an empty Sequence
    public void testLength2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s1 = this.createFromArgsTest();

        int l1 = s1.length();
        int l1Expected = 0;

        assertEquals(l1, l1Expected);

    }

}