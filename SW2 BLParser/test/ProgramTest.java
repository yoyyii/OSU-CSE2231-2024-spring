import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.program.Program;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Program}'s constructor and kernel methods.
 *
 * @author Yoyi Liao, Yutong Ye
 *
 */
public abstract class ProgramTest {

    /**
     * The names of a files containing a (possibly invalid) BL programs.
     */
    private static final String FILE_NAME_1 = "test/program1.bl";
    private static final String FILE_NAME_2 = "test/program2.bl";

    private static final String FILE_NAME_3 = "doc/Program_Duplicate_Instruction_Name.bl";
    private static final String FILE_NAME_4 = "doc/Program_Instruction_Name_Is_Primitive_Call.bl";
    private static final String FILE_NAME_5 = "doc/Program_Instruction_Name_Not_Match.bl";
    private static final String FILE_NAME_6 = "doc/Program_Instruction_No_End.bl";
    private static final String FILE_NAME_7 = "doc/Program_No_Begin.bl";
    private static final String FILE_NAME_8 = "doc/Program_No_End.bl";
    private static final String FILE_NAME_9 = "doc/Program_No_Instruction.bl";
    private static final String FILE_NAME_10 = "doc/Program_No_Is.bl";
    private static final String FILE_NAME_11 = "doc/Program_No_User_Define_Instruction.bl";
    private static final String FILE_NAME_12 = "doc/Program_Program_Name_Not_Match.bl";
    private static final String FILE_NAME_13 = "doc/Program_Program_No_Endname.bl";
    private static final String FILE_NAME_14 = "doc/Program_Instruction_No_Endname.bl";
    private static final String FILE_NAME_15 = "doc/Program_Instruction_Name_Start_With_Number.bl";

    /**
     * Invokes the {@code Program} constructor for the implementation under test
     * and returns the result.
     *
     * @return the new program
     * @ensures constructorTest = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))
     */
    protected abstract Program constructorTest();

    /**
     * Invokes the {@code Program} constructor for the reference implementation
     * and returns the result.
     *
     * @return the new program
     * @ensures constructorRef = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))
     */
    protected abstract Program constructorRef();

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    // TODO - add more test cases for valid inputs
    // TODO - add more test cases for as many distinct syntax errors as possible

    /**
     * Test of parse on program with instructions that have duplicate name.
     */
    @Test(expected = RuntimeException.class)
    public final void testDuplicateInstructionName() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_3);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with instructions that have primitive call as
     * name.
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionNameIsPrimitiveCall() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_4);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with instructions that have ending name
     * different than the name it start.
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionNameNotMatch() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_5);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with instructions that no "END" at the end.
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionNoEnd() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_6);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with no "Begin" at the start of body.
     */
    @Test(expected = RuntimeException.class)
    public final void testNoBegin() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_7);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with no "END" at the end of the program.
     */
    @Test(expected = RuntimeException.class)
    public final void testNoEnd() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_8);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with instructions that have no "INSTRUCTION".
     */
    @Test(expected = RuntimeException.class)
    public final void testNoInstruction() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_9);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program that have no "IS" after program name.
     */
    @Test(expected = RuntimeException.class)
    public final void testNoIs() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_10);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with no user define instruction.
     */
    @Test
    public final void testNoUserDefineInstruction() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_11);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with its ending name not match with its start
     * name.
     */
    @Test(expected = RuntimeException.class)
    public final void testProgramNameNotMatch() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_12);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with no program name after "END".
     */
    @Test(expected = RuntimeException.class)
    public final void testProgramNoEndname() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_13);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with no Instruction name after "END".
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionNoEndname() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_14);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on program with instruction name that start with number.
     */
    @Test(expected = RuntimeException.class)
    public final void testInstruction_Name_Start_With_Number() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_15);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

}
