import components.statement.Statement;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                // TODO - fill in case
                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement sub = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(sub);
                    s.addToBlock(i, sub);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                // TODO - fill in case

                Statement subIf = s.newInstance();
                Statement.Condition c = s.disassembleIf(subIf);

                count = countOfPrimitiveCalls(subIf);

                s.assembleIf(c, subIf);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                // TODO - fill in case

                Statement subIf = s.newInstance();
                Statement subElse = s.newInstance();

                Statement.Condition c = s.disassembleIfElse(subIf, subElse);

                count = countOfPrimitiveCalls(subIf)
                        + countOfPrimitiveCalls(subElse);

                s.assembleIfElse(c, subIf, subElse);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                // TODO - fill in case
                Statement sub = s.newInstance();
                Statement.Condition c = s.disassembleWhile(sub);
                count = countOfPrimitiveCalls(sub);
                s.assembleWhile(c, sub);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                // TODO - fill in case

                String call = s.disassembleCall();
                String[] primitive = { "move", "turnleft", "turnright",
                        "infect", "skip" };

                for (int i = 0; i < primitive.length; i++) {
                    if (primitive[i].equals(call)) {
                        count++;
                    }
                }

                s.assembleCall(call);

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;

    }

}
