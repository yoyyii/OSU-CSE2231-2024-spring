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
public final class SW2_HW30 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW30() {
    }

    /**
     * Returns the location of the next primitive instruction to execute in
     * compiled program {@code cp} given what the bug sees {@code wbs} and
     * starting from location {@code pc}.
     *
     * @param cp
     *            the compiled program
     * @param wbs
     *            the {@code CellState} indicating what the bug sees
     * @param pc
     *            the program counter
     * @return the location of the next primitive instruction to execute
     * @requires <pre>
     * [cp is a valid compiled BL program]  and
     * 0 <= pc < cp.length  and
     * [pc is the location of an instruction byte code in cp, that is, pc
     *  cannot be the location of an address]
     * </pre>
     * @ensures <pre>
     * [return the address of the next primitive instruction that
     *  should be executed in program cp given what the bug sees wbs and
     *  starting execution at address pc in program cp]
     * </pre>
     */
    public static int nextPrimitiveInstructionAddress(int[] cp, CellState wbs,
            int pc) {

        int address = pc;

        if (!isPrimitiveInstructionByteCode(cp[pc])) {
            if (conditionalJumpCondition(wbs, pc)) {
                address = nextPrimitiveInstructionAddress(cp, wbs, cp[pc + 2]);
            } else {
                address = nextPrimitiveInstructionAddress(cp, wbs, cp[pc + 1]);
            }
        }

        return address;

    }

    /**
     * BugsWorld possible cell states.
     */
    enum CellState {
        EMPTY, WALL, FRIEND, ENEMY;
    }

    /**
     * Returns whether the given integer is the byte code of a BugsWorld virtual
     * machine primitive instruction (MOVE, TURNLEFT, TURNRIGHT, INFECT, SKIP,
     * HALT).
     *
     * @param byteCode
     *            the integer to be checked
     * @return true if {@code byteCode} is the byte code of a primitive
     *         instruction or false otherwise
     * @ensures <pre>
     * isPrimitiveInstructionByteCode =
     *  [true iff byteCode is the byte code of a primitive instruction]
     * </pre>
     */
    private static boolean isPrimitiveInstructionByteCode(int byteCode) {

    }

    /**
     * Returns the value of the condition in the given conditional jump
     * {@code condJump} given what the bug sees {@code wbs}. Note that if
     * {@code condJump} is the byte code for the conditional jump
     * JUMP_IF_NOT_condition, the value returned is the value of the "condition"
     * part of the jump instruction.
     *
     * @param wbs
     *            the {@code CellState} indicating what the bug sees
     * @param condJump
     *            the byte code of a conditional jump
     * @return the value of the conditional jump condition
     * @requires [condJump is the byte code of a conditional jump]
     * @ensures <pre>
     * conditionalJumpCondition =
     *  [the value of the condition of condJump given what the bug sees wbs]
     * </pre>
     */
    private static boolean conditionalJumpCondition(CellState wbs,
            int condJump) {

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
