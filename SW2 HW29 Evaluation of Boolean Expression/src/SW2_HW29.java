import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Yoyi Liao
 *
 */
public final class SW2_HW29 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW29() {
    }

    /*
     * The given sample value represents the boolean expression: NOT ( F )
     *
     * A possible sample value promised for #tokens could be: <"NOT", "(", "F",
     * ")", "### END OF INPUT ###">
     *
     * In this case the outgoing value of tokens should be:
     * <"### END OF INPUT ###">
     *
     * Do not test for equality against "### END OF INPUT ###" or test against
     * the length of tokens. Another sample value for the same boolean
     * expression could be: <"NOT", "(", "F", ")", ")", "### END OF INPUT ###">
     *
     * In this latter case the outgoing value of tokens should be: <")",
     * "### END OF INPUT ###">
     *
     * Finally, yet another sample value for the same boolean expression could
     * be: <"NOT", "(", "F", ")">
     *
     * In this last case the outgoing value of tokens should be: <>
     *
     */

    /**
     * Evaluates a Boolean expression and returns its value.
     *
     * @param tokens
     *            the {@code Queue<String>} that starts with a bool-expr string
     * @return value of the expression
     * @updates tokens
     * @requires [a bool-expr string is a prefix of tokens]
     * @ensures <pre>
     * valueOfBoolExpr =
     *   [value of longest bool-expr string at start of #tokens]  and
     * #tokens = [longest bool-expr string at start of #tokens] * tokens
     * </pre>
     */
    public static boolean valueOfBoolExpr(Queue<String> tokens) {

        boolean value = false;

        while (tokens.length() > 0) {
            switch (tokens.dequeue()) {
                case "T":
                    value = true;
                    break;
                case "F":
                    value = false;
                    break;
                case "NOT":
                    value = !(valueOfBoolExpr(tokens));
                    break;
                case "(":
                    value = valueOfBoolExpr(tokens);
                    tokens.dequeue(); // remove ")"
                    break;
                case "AND":
                    value &= valueOfBoolExpr(tokens);
                    break;
                case "OR":
                    value |= valueOfBoolExpr(tokens);
                    break;
                default:
                    break;
            }
        }

        return value;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
