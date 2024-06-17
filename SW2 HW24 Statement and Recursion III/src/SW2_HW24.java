import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Put a short phrase describing the program here.
 *
 * @author Yoyi Liao
 *
 */
public final class SW2_HW24 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW24() {
    }

    /**
     * Converts c into the corresponding BL condition.
     *
     * @param c
     *            the Condition to convert
     * @return the BL condition corresponding to c
     * @ensures toStringCondition = [BL condition corresponding to c]
     */
    private static String toStringCondition(Condition c) {
        return c.toString();
    }

    /**
     * Pretty prints {@code this} to the given stream {@code out} {@code offset}
     * spaces from the left margin using
     * {@link components.program.Program#INDENT_SIZE Program.INDENT_SIZE} spaces
     * for each indentation level.
     *
     * @param out
     *            the output stream
     * @param offset
     *            the number of spaces to be placed before every nonempty line
     *            of output; nonempty lines of output that are indented further
     *            will, of course, continue with even more spaces
     * @updates out.content
     * @requires out.is_open and 0 <= offset
     * @ensures <pre>
     * out.content =
     *   #out.content * [this pretty printed offset spaces from the left margin
     *                   using Program.INDENT_SIZE spaces for indentation]
     * </pre>
     */

    public void prettyPrint(SimpleWriter out, int offset) {
        switch (this.kind()) {
            case BLOCK: {

                // TODO - fill in case

                for (int i = this.lengthOfBlock(); i > 0; i--) {
                    Statement sub = this.removeFromBlock(i);
                    sub.prettyPrint(out, offset + offset);
                    this.addToBlock(i, sub);
                }

                break;
            }
            case IF: {

                // TODO - fill in case
                Statement subIf = this.newInstance();
                Statement.Condition c = this.disassembleIf(subIf);

                printSpaces(out, offset);
                out.println("IF " + c + " THEN");

                subIf.prettyPrint(out, offset + offset);

                printSpaces(out, offset);
                out.println("END IF");

                this.assembleIf(c, subIf);

                break;
            }
            case IF_ELSE: {

                // TODO - fill in case

                Statement subIf = this.newInstance();
                Statement subElse = this.newInstance();
                Statement.Condition c = this.disassembleIfElse(subIf, subElse);

                printSpaces(out, offset);
                out.println("IF " + c + " THEN");

                subIf.prettyPrint(out, offset + offset);

                printSpaces(out, offset);
                out.println("ELSE");
                subElse.prettyPrint(out, offset + offset);

                printSpaces(out, offset);
                out.println("END IF");

                this.assembleIfElse(c, subIf, subElse);

                break;
            }
            case WHILE: {

                // TODO - fill in case

                Statement subWhile = this.newInstance();
                Statement.Condition c = this.disassembleWhile(subWhile);

                printSpaces(out, offset);
                out.println("WHILE " + c + " DO");

                subWhile.prettyPrint(out, offset + offset);

                printSpaces(out, offset);
                out.println("END WHILE");

                this.assembleWhile(c, subWhile);

                break;
            }
            case CALL: {

                // TODO - fill in case

                String call = this.disassembleCall();
                printSpaces(out, offset);
                out.println(call);
                assembleCall(call);

                break;
            }
            default: {
                // this will never happen...
                break;
            }
        }
    }

    /**
     * Prints the given number of spaces to the given output stream.
     *
     * @param out
     *            the output stream
     * @param numSpaces
     *            the number of spaces to print
     * @updates out.content
     * @requires out.is_open and spaces >= 0
     * @ensures out.content = #out.content * [numSpaces spaces]
     */
    private static void printSpaces(SimpleWriter out, int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            out.print(' ');
        }
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
