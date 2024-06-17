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
public final class SW2_HW23 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW23() {
    }

    /**
     * Refactors the given {@code Statement} so that every IF_ELSE statement
     * with a negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY,
     * NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by an equivalent
     * IF_ELSE with the opposite condition and the "then" and "else" BLOCKs
     * switched. Every other statement is left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @updates s
     * @ensures <pre>
     * s = [#s refactored so that IF_ELSE statements with "not"
     *   conditions are simplified so the "not" is removed]
     * </pre>
     */
    public static void simplifyIfElse(Statement s) {
        switch (s.kind()) {
            case BLOCK: {

                // TODO - fill in case

                for (int i = s.lengthOfBlock(); i > 0; i--) {
                    Statement sub = s.removeFromBlock(i);
                    simplifyIfElse(sub);
                    s.addToBlock(i, sub);
                }

                break;
            }
            case IF: {

                // TODO - fill in case
                Statement subIf = s.newInstance();
                Statement.Condition c = s.disassembleIf(subIf);

                simplifyIfElse(subIf);

                s.assembleIf(c, subIf);

                break;
            }
            case IF_ELSE: {

                // TODO - fill in case

                Statement subIf = s.newInstance();
                Statement subElse = s.newInstance();
                Statement.Condition c = s.disassembleIfElse(subIf, subElse);

                switch (c.name()) {
                    case "NEXT_IS_NOT_EMPTY": {
                        c = Condition.NEXT_IS_EMPTY;
                        simplifyIfElse(subIf);
                        simplifyIfElse(subElse);
                        s.assembleIfElse(c, subIf, subElse);
                        break;
                    }
                    case "NEXT_IS_NOT_ENEMY": {
                        c = Condition.NEXT_IS_ENEMY;
                        simplifyIfElse(subIf);
                        simplifyIfElse(subElse);
                        s.assembleIfElse(c, subIf, subElse);
                        break;
                    }

                    case "NEXT_IS_NOT_FRIEND": {
                        c = Condition.NEXT_IS_FRIEND;
                        simplifyIfElse(subIf);
                        simplifyIfElse(subElse);
                        s.assembleIfElse(c, subIf, subElse);
                        break;
                    }

                    case "NEXT_IS_NOT_WALL": {
                        c = Condition.NEXT_IS_WALL;
                        simplifyIfElse(subIf);
                        simplifyIfElse(subElse);
                        s.assembleIfElse(c, subIf, subElse);
                        break;
                    }

                }

                break;
            }
            case WHILE: {

                // TODO - fill in case

                Statement sub = s.newInstance();
                Statement.Condition c = s.disassembleWhile(sub);
                simplifyIfElse(sub);
                s.assembleWhile(c, sub);

                break;
            }
            case CALL: {
                // nothing to do here...can you explain why?
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
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
