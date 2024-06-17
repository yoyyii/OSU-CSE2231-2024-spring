import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Yoyi Liao, Yutong Ye
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        // TODO - fill in body

        // throw "INSTRUCTION" away
        tokens.dequeue();

        // assert the instruction name is an identifier"
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(tokens.front()),
                "Violation of: Instruction name is an identifier");

        String instructionName = tokens.dequeue();

        String[] primitiveCalls = { "move", "turnright", "turnleft", "infect",
                "skip" };

        // assert instruction name is not a primitive call
        for (String s : primitiveCalls) {

            Reporter.assertElseFatalError(!s.equals(instructionName),
                    "Error: the name of user-defined instruction must not be the name of the primitive instruction");
        }

        // throw away "IS"
        Reporter.assertElseFatalError(tokens.front().equals("IS"),
                "Violation of: Expect String \"IS\"");
        tokens.dequeue();

        // parse body
        body.parseBlock(tokens);

        // assert it's followed by "END instruction_name"
        Reporter.assertElseFatalError(tokens.front().equals("END"),
                "Violation of: Expect String \"END\"");
        tokens.dequeue();

        Reporter.assertElseFatalError(tokens.front().equals(instructionName),
                "Violation of: Expect String " + instructionName);
        tokens.dequeue();

        return instructionName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body

        Reporter.assertElseFatalError(tokens.front().equals("PROGRAM"),
                "Violation of: <\"PROGRAM\"> is proper prefix of tokens");

        // throw "PROGRAM" away
        tokens.dequeue();

        String programName = tokens.dequeue();

        this.setName(programName);

        // throw away "IS"
        Reporter.assertElseFatalError(tokens.front().equals("IS"),
                "Violation of: Expect String \"IS\"");
        tokens.dequeue();

        /*
         * if there is user define instruction, parse the instruction, then add
         * it to context. Make sure there's no duplicate instruction name in
         * user-defined instruction
         */

        Set<String> noDuplicateName = new Set1L<>();

        Map<String, Statement> swap = this.newContext();
        this.swapContext(swap);

        while ((tokens.front()).equals("INSTRUCTION")) {
            Statement s = this.newBody();
            String instructionName = parseInstruction(tokens, s);
            if (noDuplicateName.contains(instructionName)) {
                Reporter.assertElseFatalError(false,
                        "Violation of: The name of each new user-defined instruction must be unique");
            } else {
                swap.add(instructionName, s);
                noDuplicateName.add(instructionName);
            }

        }

        this.swapContext(swap);

        Reporter.assertElseFatalError(tokens.front().equals("BEGIN"),
                "Violation of: Expect String \"BEGIN\"");

        // throw "BEGIN" away
        tokens.dequeue();

        // parse body
        Statement s = this.newBody();
        this.swapBody(s);
        s.parseBlock(tokens);
        this.swapBody(s);

        // assert it's followed by "END program_name"
        Reporter.assertElseFatalError(tokens.front().equals("END"),
                "Violation of: Expect String \"END\"");
        tokens.dequeue();

        Reporter.assertElseFatalError(tokens.front().equals(programName),
                "Violation of: Expect String " + programName);
        tokens.dequeue();

        // assert there's an end of input string

        Reporter.assertElseFatalError(
                tokens.front().equals("### END OF INPUT ###"),
                "Violation of: Expect \"### END OF INPUT ###\"");
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
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
