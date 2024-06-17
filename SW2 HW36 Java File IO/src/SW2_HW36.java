import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
public final class SW2_HW36 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW36() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L(args[0]);
        SimpleWriter out = new SimpleWriter1L(args[1]);

        /*
         * Close input and output streams
         */

        while (!in.atEOS()) {
            String curr = in.nextLine();
            out.println();
        }

        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(args[1])));
        String s = in.readLine();
        while (s != null) {
            s = in.readLine();
            out.println();
        }

        in.close();
        out.close();
    }

    public static void main(String[] args) {
        BufferedReader in;
        PrintWriter out;

        // Code to open file
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
        } catch (IOException e) {
            System.err.println("Error opening file");
            return;
        }

        // Code to read file
        try {

            String s = in.readLine();
            while (s != null) {
                out.println();
                s = in.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }

        // Code to close file
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
        }
    }

}
