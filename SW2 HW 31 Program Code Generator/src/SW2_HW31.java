import components.map.Map;
import components.program.Program.Instruction;
import components.sequence.Sequence;
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
public final class SW2_HW31 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW31() {
    }

    /**
     * Converts {@code Condition} into corresponding conditional jump
     * instruction byte code.
     *
     * @param c
     *            the {@code Condition} to be converted
     * @return the conditional jump instruction byte code corresponding to
     *         {@code c}
     * @ensures <pre>
     * conditionalJump =
     *  [conditional jump instruction byte code corresponding to c]
     * </pre>
     */
    private static Instruction conditionalJump(Condition c) {
        Instruction instruction;

        String condition = c.toString();

        switch (condition) {
            case "NEXT_IS_EMPTY": {
                instruction = Instruction.JUMP_IF_NOT_NEXT_IS_EMPTY;
            }

            case "NEXT_IS_WALL": {
                instruction = Instruction.JUMP_IF_NOT_NEXT_IS_WALL;
            }

            case "NEXT_IS_FRIEND": {
                instruction = Instruction.JUMP_IF_NOT_NEXT_IS_FRIEND;
            }

            case "NEXT_IS_ENEMY": {
                instruction = Instruction.JUMP_IF_NOT_NEXT_IS_ENEMY;

            }

            case "NEXT_IS_NOT_EMPTY": {
                instruction = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_EMPTY;
            }

            case "NEXT_IS_NOT_WALL": {
                instruction = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_WALL;
            }

            case "NEXT_IS_NOT_FRIEND": {
                instruction = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_FRIEND;
            }

            case "NEXT_IS_NOT_ENEMY": {
                instruction = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_ENEMY;

            }

            default: {
                instruction = Instruction.JUMP;
            }
        }

        return instruction;
    }

    /*
     * Generates the sequence of virtual machine instructions ("byte codes")
     * corresponding to {@code s} and appends it at the end of {@code cp}.
     *
     * @param s the {@code Statement} for which to generate code
     *
     * @param context the {@code Context} in which to find user defined
     * instructions
     *
     * @param cp the {@code Sequence} containing the generated code
     *
     * @updates cp
     *
     * @ensures <pre> if [all instructions called in s are either primitive or
     * defined in context] and [context does not include any calling cycles,
     * i.e., recursion] then cp = #cp * [sequence of virtual machine
     * "byte codes" corresponding to s] else [reports an appropriate error
     * message to the console and terminates client] </pre>
     */
    private static void generateCodeForStatement(Statement s,
            Map<String, Statement> context, Sequence<Integer> cp) {

        final int dummy = 0;

        switch (s.kind()) {
            case BLOCK: {

                // TODO - fill in case

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement curr = s.removeFromBlock(i);
                    generateCodeForStatement(curr, context, cp);
                    s.addToBlock(i, curr);
                }

                break;
            }
            case IF: {
                Statement b = s.newInstance();
                Condition c = s.disassembleIf(b);
                cp.add(cp.length(), conditionalJump(c).byteCode());
                int jump = cp.length();
                cp.add(cp.length(), dummy);
                generateCodeForStatement(b, context, cp);
                cp.replaceEntry(jump, cp.length());
                s.assembleIf(c, b);
                break;
            }
            case IF_ELSE: {

                // TODO - fill in case

                Statement subIf = s.newInstance();
                Statement subElse = s.newInstance();
                Condition c = s.disassembleIfElse(subIf, subElse);

                cp.add(cp.length(), conditionalJump(c).byteCode());

                /*
                 * place holder for where should jump to if condition not met,
                 * aka jump to else statement
                 */
                int ifNotMet = cp.length();
                cp.add(cp.length(), dummy);

                // generate code for if statement
                generateCodeForStatement(subIf, context, cp);

                /*
                 * place holder for where should jump to after the if statement
                 * have finished execute, aka skip over else statement
                 */
                cp.add(cp.length(), Instruction.valueOf("JUMP").byteCode());
                int jump = cp.length();
                cp.add(cp.length(), dummy);

                /*
                 * replace the value of place holder for if condition not met to
                 * with the length of sequence, aka the end of the body of if
                 */
                cp.replaceEntry(ifNotMet, cp.length());

                // generate code for else statement
                generateCodeForStatement(subElse, context, cp);

                /*
                 * replace the value of place holder for else with the length of
                 * sequence, aka the end of the body of else
                 */
                cp.replaceEntry(jump, cp.length());

                // reassemble the statement
                s.assembleIfElse(c, subIf, subElse);

                break;
            }
            case WHILE: {

                // TODO - fill in case

                Statement subWhile = s.newInstance();
                Condition c = s.disassembleWhile(subWhile);

                /*
                 * address of the start of the while loop so we could loop back
                 * after each iteration
                 */
                int start = cp.length();

                /*
                 * place holder for where should jump to when the condition is
                 * false
                 */

                // add the while condition into sequence
                cp.add(cp.length(), conditionalJump(c).byteCode());

                int jump = cp.length();

                // where to jump to is the next line right after condition
                cp.add(cp.length(), dummy);

                // generate code for while body
                generateCodeForStatement(subWhile, context, cp);

                /*
                 * replace place holder with the line right after the end of
                 * while body
                 */
                cp.replaceEntry(jump, cp.length());
                s.assembleWhile(c, subWhile);

                break;
            }
            case CALL: {

                // TODO - fill in case

                String call = s.disassembleCall();

                // if the call is an user define function...
                if (context.hasKey(call)) {
                    /*
                     * go into context and do what user define function says
                     */
                    generateCodeForStatement(context.value(call),
                            context.newInstance(), cp);
                } else {
                    /*
                     * else, it must be a primitive instruction. Add the
                     * instruction directly into the sequence
                     */
                    String primitive = call.toUpperCase();
                    cp.add(cp.length(),
                            Instruction.valueOf(primitive).byteCode());
                }

                s.assembleCall(call);
                break;
            }

            default: {
                // nothing to do here: this will never happen...
                break;
            }
        }
    }

    /**
     * Generates and returns the sequence of virtual machine instructions ("byte
     * codes") corresponding to {@code this}.
     *
     * @return the compiled program
     * @ensures <pre>
     * if [all instructions called in this are either primitive or
     *     defined in this.context]  and
     *    [this does not include any calling cycles, i.e., recursion] then
     *  generatedCode =
     *   [the sequence of virtual machine "byte codes" corresponding to this]
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    public Sequence<Integer> generatedCode() {

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
