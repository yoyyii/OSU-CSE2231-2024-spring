import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Yutong Ye, Yoyi Liao
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        Reporter.assertElseFatalError(Tokenizer.isCondition(c),
                "Expected to a CONDITION in tokens.");
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";

        // TODO - fill in body
        //IF keyword
        Reporter.assertElseFatalError(tokens.front().equals("IF"),
                "Expected to IF in tokens in if statement");
        tokens.dequeue();

        //Generate condition
        String con = tokens.dequeue();
        Condition condition = parseCondition(con);

        //THEN keyword
        Reporter.assertElseFatalError(tokens.front().equals("THEN"),
                "Expected to THEN in tokens in if statement");
        tokens.dequeue();

        //if-statement main body
        Statement block = s.newInstance(); // if block
        block.parseBlock(tokens);
        //else-if-statement main body
        if (tokens.front().equals("ELSE")) {
            tokens.dequeue();
            Statement newBlock = s.newInstance(); // if-else block
            newBlock.parseBlock(tokens);
            s.assembleIfElse(condition, block, newBlock);
        } else {
            s.assembleIf(condition, block);
        }

        //END keyword
        Reporter.assertElseFatalError(tokens.front().equals("END"),
                "Expected to END in tokens in if statement ending");
        tokens.dequeue();

        //IF keyword
        Reporter.assertElseFatalError(tokens.front().equals("IF"),
                "Expected to IF in tokens in if statement ending");
        tokens.dequeue();
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";

        // TODO - fill in body
        //WHILE keyword
        Reporter.assertElseFatalError(tokens.front().equals("WHILE"),
                "Expected to WHILE in tokens in while statement");
        tokens.dequeue();

        //Generate condition
        String con = tokens.dequeue();
        Condition condition = parseCondition(con);

        //DO keyword
        Reporter.assertElseFatalError(tokens.front().equals("DO"),
                "Expected to DO in tokens in while statement");
        tokens.dequeue();

        //while-loop main body
        Statement block = s.newInstance();
        block.parseBlock(tokens);

        //END keyword
        Reporter.assertElseFatalError(tokens.front().equals("END"),
                "Expected to END in tokens in while statement ending");
        tokens.dequeue();

        //WHILE keyword
        Reporter.assertElseFatalError(tokens.front().equals("WHILE"),
                "Expected to WHILE in tokens in while statement ending");
        tokens.dequeue();

        //assemble while loop
        s.assembleWhile(condition, block);
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";

        // TODO - fill in body
        //Generate one call.
        String temp = tokens.dequeue();
        s.assembleCall(temp);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body
        String temp = tokens.front();
        if (temp.equals("IF")) {
            parseIf(tokens, this);
        } else if (temp.equals("WHILE")) {
            parseWhile(tokens, this);
        } else if (Tokenizer.isIdentifier(temp)) {
            parseCall(tokens, this);
        } else {
            Reporter.assertElseFatalError(true,
                    "Violation of: Expected a statement, the tokens has wrong context");
        }

    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body
        String temp = tokens.front();
        int index = 0;
        //iterate for all the children of block.
        while (temp.equals("IF") || temp.equals("WHILE")
                || Tokenizer.isIdentifier(temp)) {
            Statement newBlock = this.newInstance();
            newBlock.parse(tokens);
            this.addToBlock(index, newBlock);
            index++;
            temp = tokens.front();
        }
    }

    /*
     * Main test method -------------------------------------------------------
     */

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
         * Get input file name
         */
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
