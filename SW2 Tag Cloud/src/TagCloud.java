import java.util.Comparator;

import components.map.Map;
import components.map.Map2;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine2;
import components.utilities.Reporter;

/**
 * The program shall ask the user for the name of an input file, for the name of
 * an output file, and for the number of words to be included in the generated
 * tag cloud (a positive integer, say N). The program shall respect the user
 * input as being the complete relative or absolute path as the name of the
 * input file, or the name of the output file, and will not augment the given
 * path in any way, e.g, it will not supply its own filename extension. For
 * example, a reasonable user response for the name of the input file could
 * directly result in the String value "data/importance.txt"; similarly, a
 * reasonable user response for the name of the output file could directly
 * result in the String value "data/importance.html". The output shall be a
 * single well-formed HTML file displaying the name of the input file in a
 * heading followed by a tag cloud of the N words with the highest count in the
 * input. The words shall appear in alphabetical order.
 *
 * @author Yoyi Liao, Yutong Ye
 */
public final class TagCloud {
    /**
     * Default constructor--private to prevent instantiation.
     */
    private TagCloud() {
        // no code needed here
    }

    /**
     * Build word comparator for sorting in alphabetical order. Whether the
     * letter is upper case or lower case will not influence the sorting order.
     */
    private static class WordComparator
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> map1,
                Map.Pair<String, Integer> map2) {
            return map1.key().compareTo(map2.key());
        }
    }

    /**
     * Build count comparator for sorting in decreasing order.
     */
    private static class CountComparator
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> map1,
                Map.Pair<String, Integer> map2) {
            int count1 = map1.value();
            int count2 = map2.value();
            if (count1 > count2) {
                return -1;
            } else if (count1 < count2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * List words and count the numbers of words appearing in text given. Store
     * those pairs in wordCounter map.
     *
     * @param textFile
     *            the original reading text
     * @param wordList
     *            the queue that stores all words.
     * @param wordCounter
     *            the map that stores all the words and the numbers they appears
     *            in text given.
     * @updates wordList, wordCounter
     * @ensures wordList = a*word appearing in text*b && wordCounter = a *
     *          <word, count>*b
     */
    private static void getWordsInMap(SimpleReader textFile,
            Map<String, Integer> wordCounter) {
        assert textFile != null : "Violation of: textFile is not null";
        assert textFile.isOpen() : "Violation of: textFile.is_open";
        assert wordCounter != null : "Violation of: wordCounter is not null";

        //Set up the separator set to ensure there is no separator in word list.
        Set<Character> separator = new Set1L<>();
        separator.add(',');
        separator.add('.');
        separator.add(' ');
        separator.add('!');
        separator.add('?');
        separator.add(':');
        separator.add('-');
        separator.add('\t');
        separator.add('\n');
        separator.add('\r');
        separator.add('[');
        separator.add(']');
        separator.add(';');
        separator.add('/');
        separator.add('(');
        separator.add(')');
        separator.add('\'');
        separator.add('\"');
        separator.add('*');
        separator.add('_');
        separator.add('`');
        separator.add('{');
        separator.add('}');
        separator.add('+');
        separator.add('=');
        separator.add('\\');
        separator.add('|');

        while (!textFile.atEOS()) {
            String oneLine = textFile.nextLine();
            oneLine = oneLine.toLowerCase();
            int index = 0;
            while (index < oneLine.length()) {
                String wordUpperOrLower = nextWordOrSeparator(oneLine, index,
                        separator);
                String word = wordUpperOrLower.toLowerCase();

                if (Character.isLetter(word.charAt(0))) {

                    if (wordCounter.hasKey(word)) {
                        wordCounter.replaceValue(word,
                                wordCounter.value(word) + 1);
                    } else {
                        wordCounter.add(word, 1);
                    }
                }
                index += word.length();
            }
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String term = "";
        final int startingPosition = position;
        if (!separators.contains(text.charAt(position))) {
            while (position < text.length()
                    && (Character.isLetter(text.charAt(position))
                            || Character.isDigit(text.charAt(position)))) {
                position += 1;
            }
        } else {
            while (position < text.length()
                    && !Character.isLetter(text.charAt(position))) {
                position += 1;
            }
        }
        term = text.substring(startingPosition, position);
        return term;
    }

    /**
     * Print the cloud word in HTML.
     *
     * @param out
     *            the output stream
     * @param folder
     *            where the page will be saved
     * @param fileIn
     *            a string of the name of the input file
     * @param N
     *            The number of words to be displayed
     */
    public static void printFont(SimpleWriter out,
            Map.Pair<String, Integer> pair, int difference) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert pair != null : "Violation of: pair is not null";

        int count = pair.value();
        String word = pair.key();
        int fontNumber = 11;
        if (difference != 0) {
            fontNumber = difference * (count - 11);
        }
        String font = "f" + fontNumber;
        out.println("<span style=\"cursor:default\" class=\"" + font
                + "\" title=\"count: " + count + "\">" + word + "</span>");
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
    public static void printHTMLHeading(SimpleWriter out, String title, int N) {

        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert title != null : "Violation of: title is not null";

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> top " + N + " words in " + title + "</title>");
        out.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println(
                "<link href=\"lib\tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<hr>");
        out.println("<div class=cdiv>");
        out.println("<p class=cbox>");

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

        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Out print the web page
     *
     * @param folder
     *            where the page will be saved
     * @param fileIn
     *            a string of the name of the input file
     * @param N
     *            The number of words to be displayed
     *
     * @ensures a proper html file that contains the word cloud of the words
     *          that appeared the most in the user input file.
     */
    public static void printHomePage(String folder, String fileIn, int N) {
        assert folder != null : "Violation of: folder is not null";
        assert fileIn != null : "Violation of: fileIn is not null";
        assert folder != null : "Violation of: folder is not null";
        assert fileIn != null : "Violation of: fileIn is not null";

        SimpleWriter out = new SimpleWriter1L(folder);
        out.println("<h2> Top " + N + " words in " + fileIn + "</h2>");
        printHTMLHeading(out, folder, N);
        printCloud(out, folder, fileIn, N);
        printHTMLFooter(out);
    }

    /**
     * Print the cloud word in HTML.
     *
     * @param out
     *            the output stream
     * @param folder
     *            where the page will be saved
     * @param fileIn
     *            a string of the name of the input file
     * @param N
     *            The number of words to be displayed
     */
    public static void printCloud(SimpleWriter out, String folder,
            String fileIn, int N) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert folder != null : "Violation of: folder is not null";
        assert fileIn != null : "Violation of: fileIn is not null";

        SimpleReader texts = new SimpleReader1L(fileIn);
        Map<String, Integer> wordCounter = new Map2<>();
        getWordsInMap(texts, wordCounter);

        Comparator<Map.Pair<String, Integer>> countComparator = new CountComparator();
        SortingMachine<Map.Pair<String, Integer>> countSort = new SortingMachine2<>(
                countComparator);

        Comparator<Map.Pair<String, Integer>> wordComparator = new CountComparator();
        SortingMachine<Map.Pair<String, Integer>> wordSort = new SortingMachine2<>(
                wordComparator);

        for (Map.Pair<String, Integer> temp : wordCounter) {
            countSort.add(temp);
        }
        countSort.order();
        countSort.changeToExtractionMode();
        int maxCount = 0;
        int minCount = 0;
        for (int i = 0; i < N; i++) {
            Map.Pair<String, Integer> temp = countSort.removeFirst();
            wordSort.add(temp);
            if (temp.value() > maxCount) {
                maxCount = temp.value();
            }
            if (temp.value() < minCount) {
                minCount = temp.value();
            }
        }
        wordSort.order();
        int difference = maxCount - minCount;
        for (Map.Pair<String, Integer> temp : wordSort) {
            printFont(out, temp, difference);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Get the input file's name and the name of the file to write to.
        out.print("Enter the name of the input text file: ");
        String inputFile = in.nextLine();

        out.print("Enter the name of the output file: ");
        String outputFile = in.nextLine();

        out.print(
                "Enter number of words to be included in the generated tag cloud: ");
        int N = Integer.parseInt(in.nextLine());

        Reporter.assertElseFatalError(N > 0,
                "Violation of: Expecting a positive number");

        printHomePage(outputFile, inputFile, N);

        in.close();
        out.close();
    }

}