import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * The objective of the Java program is to count word occurrences in a given
 * input file and generate an HTML document displaying the words and their
 * counts in alphabetical order. The program should prompt the user for the
 * input file's name and the desired output file's name. It should create a
 * well-formed HTML file that includes a heading with the name of the input file
 * and a table listing words and their counts. The words should be sorted
 * alphabetically. The definition of a word is left to the implementer, with the
 * requirement that words do not contain whitespace characters. The program
 * should respect the user's input for file names, considering them as complete
 * relative or absolute paths without adding any implied sub-path or filename
 * extensions.
 *
 * @author Yoyi Liao
 *
 */
public final class wordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private wordCounter() {
    }

    /**
     * outprint the heading of html file
     *
     * @param out
     *            the output stream
     * @param title
     *            the title of the page
     * @requires out.is_open
     *
     * @ensures out.content = #out.content * [the html heading tags]
     */
    public static void printHTMLHeading(SimpleWriter out, String title) {

        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
    }

    /**
     * Read input file and maps it into maps that has {@code key} as key,
     * {@code value} as value.
     *
     * @param in
     *            the input file reader
     *
     * @param count
     *            map we wish to update that map each word to its word count
     *
     * @update count
     *
     * @return a queue that contains all the unique word appears in in
     *
     * @requires {@code dictionary} != null
     *
     * @requires in.is_open
     *
     * @ensures {@code count} pairs each word with its count
     *
     *          createMapAndQueue contains all words in in
     */
    public static Queue<String> createMapAndQueue(SimpleReader in,
            Map<String, Integer> count) {

        assert count != null : "Violation of: count is not null";

        Queue<String> sorted = new Queue1L<>();

        while (!in.atEOS()) {
            String next = in.nextLine();
            next = next.toLowerCase();

            String curr = "";

            for (char c : next.toCharArray()) {
                if (Character.isWhitespace(c)) {
                    if (count.hasKey(curr)) {
                        count.replaceValue(curr, count.value(curr) + 1);
                    } else {
                        if (curr != "") {
                            count.add(curr, 1);
                            sorted.enqueue(curr);
                        }

                    }

                    curr = "";
                } else if (Character.isLetter(c) || Character.isDigit(c)) {
                    curr += c;
                }
            }
        }

        return sorted;

    }

    /**
     * outprint the web page of the word count
     *
     * @param folder
     *            where the page will be saved
     *
     * @param fileIn
     *            a string of the name of the input file
     *
     * @param mapSorted
     *            a queue that contains all the unique word in sorted order
     *
     * @param count
     *            the map that maps each word to its count
     *
     * @ensures a proper web page that contains the table that shows each word
     *          with its count of appearance in the text
     */
    public static void printHomePage(String folder, String fileIn,
            Queue<String> mapSorted, Map<String, Integer> count) {

        SimpleWriter out = new SimpleWriter1L(folder + "/wordCount.html");

        String title = "Words Counted in " + fileIn;
        printHTMLHeading(out, title);

        out.println("<h2>" + title + "</h2>");

        out.println("<hr>");

        printTable(out, mapSorted, count);

        printHTMLFooter(out);
    }

    /**
     * outprint the table that maps each word to its count
     *
     * @param out
     *            the output stream
     * @param mapSorted
     *            a sorted queue of all unique words appear in the text
     * @param count
     *            a map that map each unique word to its number of appearance
     * @requires out.is_open
     *
     * @ensures out.content = #out.content * [the html closing tags]
     */

    public static void printTable(SimpleWriter out, Queue<String> mapSorted,
            Map<String, Integer> count) {

        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<table border=\"1\">");
        out.println("<tbody>");

        out.println("<tr>");
        out.println("<th>Words</th>");
        out.println("<th>Counts</th>");
        out.println("</tr>");

        for (String str : mapSorted) {
            out.println("<tr>");
            out.println("<td>" + str + "</td>");
            out.println("<td>" + count.value(str) + "</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");

    }

    /**
     * outprint the footer of html file
     *
     * @param out
     *            the output stream
     *
     * @requires out.is_open
     *
     * @ensures out.content = #out.content * [the html closing tags]
     */

    public static void printHTMLFooter(SimpleWriter out) {

        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {
        if (q.length() != 0) {

            String temp = removeMin(q, order);
            sort(q, order);
            q.enqueue(temp);
        }
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    public static String removeMin(Queue<String> q, Comparator<String> order) {
        String min = "";
        Queue<String> copyQ = new Queue1L<>();

        for (String s : q) {

            if (min.equals("")) {//initial condition
                min = s;
            }

            int comp = order.compare(min, s);

            if (comp < 0) { // min < s
                min = s;
            }

            copyQ.enqueue(s);
        }

        for (String s : copyQ) {
            String temp = q.dequeue();
            if (!s.equals(min)) {
                q.enqueue(temp);
            }
        }
        return min;
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
         * enter file name
         */
        out.print("Enter the name of the input file : ");
        String fileIn = in.nextLine();

        // how to read the file input
        SimpleReader fileReader = new SimpleReader1L(fileIn);

        /*
         * enter the output file name
         */
        out.print(
                "Enter the name of a folder where all the output files will be saved (The output folder must already exist)");

        String folder = in.nextLine();

        // create a map to map each term with its definition and create a sorted queue
        Map<String, Integer> count = new Map1L<>();
        Queue<String> terms = createMapAndQueue(fileReader, count);

        Comparator<String> strCompare = new StringComparator();
        sort(terms, strCompare);

        //print the index page
        printHomePage(folder, fileIn, terms, count);

        /*
         * Close I/O streams.
         */

        in.close();
        out.close();

    }

    private static class StringComparator implements Comparator<String> {
        /**
         * compares which String is bigger.
         *
         * @param str1
         *            the first String
         * @param str2
         *            the second String
         * @return 1 if {@code str1} is larger than {@code str2} -1 if
         *         {@code str1} is larger than {@code str2} 0 if @code str1}
         *         equals to {@code str2}
         */
        @Override
        public int compare(String s1, String s2) {
            if (s1.compareTo(s2) < 0) {
                return -1;
            } else if (s1.compareTo(s2) > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
