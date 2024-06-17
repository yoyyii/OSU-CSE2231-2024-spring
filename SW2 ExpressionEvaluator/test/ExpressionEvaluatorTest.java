import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code ExpressionEvaluator}'s {@code valueOfExpr}
 * static method.
 *
 * @author Yoyi Liao
 *
 */
public final class ExpressionEvaluatorTest {

    @Test
    public void testExample() {
        StringBuilder exp = new StringBuilder(
                "281/7/2-1-5*(15-(14-1))+((1))+20=30!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(30, value);
        assertEquals("=30!", exp.toString());
    }

    // TODO - add other (simpler) test cases to help with debugging

    @Test
    public void test1() {
        StringBuilder exp = new StringBuilder("18-5+9/90*5=13!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(13, value);
        assertEquals("=13!", exp.toString());
    }

    @Test
    public void test2() {
        StringBuilder exp = new StringBuilder("20*((3+4)-3)-3/(3*4)=79!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(79, value);
        assertEquals("=79!", exp.toString());
    }

    @Test
    public void test3() {
        StringBuilder exp = new StringBuilder("1+2*3-4=3!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(3, value);
        assertEquals("=3!", exp.toString());
    }

}
