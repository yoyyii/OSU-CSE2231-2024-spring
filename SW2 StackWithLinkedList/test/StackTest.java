import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Elijah Bulluck
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    // TODO - add test cases for constructor, push, pop, and length

    /*
     * test constructor
     */

    @Test
    public final void testConstructor() {
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef();

        assertEquals(s, sExpected);
    }

    /*
     * test push
     */

    @Test
    public final void testPush1() {
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("a");
        s.push("a");
        assertEquals(s.length(), 1);
        assertEquals(s, sExpected);

    }

    @Test
    public final void testPush2() {
        Stack<String> s = this.createFromArgsTest("b", "c");
        Stack<String> sExpected = this.createFromArgsRef("a", "b", "c");
        s.push("a");
        assertEquals(s.length(), 3);
        assertEquals(s, sExpected);

    }

    /*
     * test pop
     */

    @Test
    public final void testPop1() {
        Stack<String> s = this.createFromArgsTest("c");
        Stack<String> sExpected = this.constructorRef();
        s.pop();
        assertEquals(s.length(), 0);
        assertEquals(s, sExpected);

    }

    @Test
    public final void testPop2() {
        Stack<String> s = this.createFromArgsTest("a", "b", "c");
        Stack<String> sExpected = this.createFromArgsRef("b", "c");
        s.pop();
        assertEquals(s.length(), 2);
        assertEquals(s, sExpected);

    }

    /*
     * test length
     */

    @Test
    public final void testLength1() {
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.constructorRef();
        int len = s.length();
        assertEquals(len, 0);
        assertEquals(s, sExpected);

    }

    @Test
    public final void testLength2() {
        Stack<String> s = this.createFromArgsTest("a", "b", "c");
        Stack<String> sExpected = this.createFromArgsRef("a", "b", "c");
        int len = s.length();
        assertEquals(len, 3);
        assertEquals(s, sExpected);

    }

}