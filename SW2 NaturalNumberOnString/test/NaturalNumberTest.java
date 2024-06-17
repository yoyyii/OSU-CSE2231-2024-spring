import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Yutong Ye, Yoyi Liao
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /*
     * test default constructors
     */

    @Test
    public final void testDefaultConstructor() {

        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(nExpected, n);
    }

    /*
     * test int constructors
     */

    @Test
    //when parameter is a single digit
    public final void testIntConstructor1() {

        int temp = 1;
        NaturalNumber n = this.constructorTest(temp);
        NaturalNumber nExpected = this.constructorRef(temp);

        assertEquals(nExpected, n);
    }

    @Test
    //when parameter have more than 1 digits
    public final void testIntConstructor2() {

        int temp = 10;
        NaturalNumber n = this.constructorTest(temp);
        NaturalNumber nExpected = this.constructorRef(temp);

        assertEquals(nExpected, n);
    }

    @Test
    //when parameter is zero
    public final void testIntConstructor3() {

        int temp = 0;
        NaturalNumber n = this.constructorTest(temp);
        NaturalNumber nExpected = this.constructorRef(temp);

        assertEquals(nExpected, n);
    }

    /*
     * test string constructors
     */

    @Test
    //when parameter is zero
    public final void testStringConstructor1() {

        String temp = "0";

        NaturalNumber n = this.constructorTest(temp);
        NaturalNumber nExpected = this.constructorRef(temp);

        assertEquals(nExpected, n);
    }

    @Test
    //when parameter have the length of 1
    public final void testStringConstructor2() {

        String temp = "1";

        NaturalNumber n = this.constructorTest(temp);
        NaturalNumber nExpected = this.constructorRef(temp);

        assertEquals(nExpected, n);
    }

    @Test
    //when parameter have the length that is greater than 1
    public final void testStringConstructor3() {

        String temp = "10";

        NaturalNumber n = this.constructorTest(temp);
        NaturalNumber nExpected = this.constructorRef(temp);

        assertEquals(nExpected, n);
    }

    /*
     * test natural number constructors
     */

    @Test
    //when parameter is created using Natural Number default constructor
    public final void testNNConstructor() {

        NaturalNumber temp = new NaturalNumber3();

        NaturalNumber n = this.constructorTest(temp);
        NaturalNumber nExpected = this.constructorRef(temp);

        assertEquals(nExpected, n);
    }

    /*
     * test multiplyBy10
     */

    @Test
    //test multiplyBy10 when n is not zero
    public final void testMultiplyBy10_1() {

        NaturalNumber n = this.constructorTest(20);
        NaturalNumber nExpected = this.constructorRef(202);

        n.multiplyBy10(2);

        assertEquals(nExpected, n);
    }

    @Test
    //test multiplyBy10 when n is zero
    public final void testMultiplyBy10_2() {

        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(2);

        n.multiplyBy10(2);

        assertEquals(nExpected, n);
    }

    /*
     * test divideBy10
     */

    @Test
    //test divideBy10 when n has more than 1 digits
    public final void testDivideBy10_1() {

        NaturalNumber n = this.constructorTest(23);
        NaturalNumber nExpected = this.constructorRef(2);

        int last = n.divideBy10();

        assertEquals(3, last);
        assertEquals(nExpected, n);
    }

    @Test
    //test divideBy10 when n is zero
    public final void testDivideBy10_2() {

        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        int last = n.divideBy10();

        assertEquals(0, last);
        assertEquals(nExpected, n);
    }

    @Test
    //test divideBy10 when n has 1 digits
    public final void testDivideBy10_3() {

        NaturalNumber n = this.constructorTest(2);
        NaturalNumber nExpected = this.constructorRef(0);

        int last = n.divideBy10();

        assertEquals(2, last);
        assertEquals(nExpected, n);
    }

    /*
     * test isZero
     */

    @Test
    //test isZero when the NaturalNumber is zero
    public final void testIsZero1() {

        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);

        assertEquals(n.isZero(), true);
        assertEquals(nExpected, n);
    }

    @Test
    //test isZero when the NaturalNumber is not zero
    public final void testIsZero2() {

        NaturalNumber n = this.constructorTest(20);
        NaturalNumber nExpected = this.constructorRef(20);

        assertEquals(n.isZero(), false);
        assertEquals(nExpected, n);
    }
}
