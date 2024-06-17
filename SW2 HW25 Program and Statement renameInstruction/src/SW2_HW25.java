import components.map.Map;
import components.program.Program;
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
public final class SW2_HW25 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW25() {
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
     * Refactors the given {@code Statement} by renaming every occurrence of
     * instruction {@code oldName} to {@code newName}. Every other statement is
     * left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates s
     * @requires [newName is a valid IDENTIFIER]
     * @ensures <pre>
     * s = [#s refactored so that every occurrence of instruction oldName
     *   is replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Statement s, String oldName,
            String newName) {

        switch (s.kind()) {
            case BLOCK: {
                for (int i = s.lengthOfBlock(); i > 0; i--) {
                    Statement curr = s.removeFromBlock(i);
                    renameInstruction(curr, oldName, newName);
                    s.addToBlock(i, curr);
                }
                break;
            }
            case IF: {
                Statement subIf = s.newInstance();
                Condition c = s.disassembleIf(subIf);
                renameInstruction(subIf, oldName, newName);
                s.assembleIf(c, subIf);
                break;
            }
            case IF_ELSE: {
                Statement subIf = s.newInstance();
                Statement subElse = s.newInstance();
                Condition c = s.disassembleIfElse(subIf, subElse);
                renameInstruction(subIf, oldName, newName);
                renameInstruction(subElse, oldName, newName);
                s.assembleIfElse(c, subIf, subElse);
                break;
            }
            case WHILE: {
                Statement subWhile = s.newInstance();
                Condition c = s.disassembleWhile(subWhile);
                renameInstruction(subWhile, oldName, newName);
                s.assembleWhile(c, subWhile);
                break;
            }
            case CALL: {
                String call = s.disassembleCall();
                if (call.equals(oldName)) {
                    call = newName;
                }
                s.assembleCall(call);

                break;
            }
            default: {
                break;
            }
        }

    }

    /**
     * Refactors the given {@code Program} by renaming instruction
     * {@code oldName}, and every call to it, to {@code newName}. Everything
     * else is left unmodified.
     *
     * @param p
     *            the {@code Program}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates p
     * @requires <pre>
     * oldName is in DOMAIN(p.context)  and
     * [newName is a valid IDENTIFIER]  and
     * newName is not in DOMAIN(p.context)
     * </pre>
     * @ensures <pre>
     * p = [#p refactored so that instruction oldName and every call
     *   to it are replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Program p, String oldName,
            String newName) {
        Map<String, Statement> ctxt = p.newContext();
        Map<String, Statement> c = p.newContext();
        p.swapContext(ctxt);

        while (ctxt.size() > 0) {

            Map.Pair<String, Statement> instr = ctxt.removeAny();
            String key = instr.key();
            if (key.equals(oldName)) {
                key = newName;

            }

            renameInstruction(instr.value(), oldName, newName);
            c.add(key, instr.value());
        }

        p.swapContext(c);

        Statement pBody = p.newBody();
        p.swapBody(pBody);
        renameInstruction(pBody, oldName, newName);
        p.swapBody(pBody);
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
