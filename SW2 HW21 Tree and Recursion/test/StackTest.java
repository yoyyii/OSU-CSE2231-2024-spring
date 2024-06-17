import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
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
    //add an element to the empty set
    public void testConstructor1() {
        Stack<String> s = this.createFromArgsRef();
        Stack<String> sExpected = this.createFromArgsTest();

        assertEquals(sExpected, s);
    }

    /*
     * test push
     */

    @Test
    //add an element to the empty stack
    public void testPush1() {
        Stack<String> s = this.createFromArgsRef();
        Stack<String> sExpected = this.createFromArgsTest("a");

        s.push("a");

        assertEquals(sExpected, s);
    }

    @Test
    //add an element to the non empty stack
    public void testPush2() {
        Stack<String> s = this.createFromArgsRef("a", "b", "c");
        Stack<String> sExpected = this.createFromArgsTest("a", "b", "c", "d");

        s.push("d");

        assertEquals(sExpected, s);
    }

    /*
     * test pop
     */

    @Test
    //pop the element to a stack with 1 element
    public void testPop1() {
        Stack<String> s = this.createFromArgsRef("a");
        Stack<String> sExpected = this.createFromArgsTest();

        String pop = s.pop();

        assertEquals(sExpected, s);
        assertEquals("a", pop);
    }

    @Test
    //add an element to the non empty stack
    public void testPop2() {
        Stack<String> s = this.createFromArgsRef("a", "b", "c");
        Stack<String> sExpected = this.createFromArgsTest("b", "c");

        String pop = s.pop();

        assertEquals(sExpected, s);
        assertEquals("a", pop);
    }

    /*
     * test length
     */

    @Test
    //length of an empty stack
    public void testLength1() {
        Stack<String> s = this.createFromArgsRef();

        int length = s.length();

        assertEquals(0, length);
    }

    @Test
    //length of a non empty stack
    public void testLength2() {
        Stack<String> s = this.createFromArgsRef("a", "b", "c");

        int length = s.length();

        assertEquals(3, length);
    }
}