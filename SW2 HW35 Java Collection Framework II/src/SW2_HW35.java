import components.map.Map;
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
public final class SW2_HW35 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SW2_HW35() {
    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires [the salaries in map are positive] and raisePercent > 0
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    private static void giveRaise(components.map.Map<String, Integer> map,
            char initial, int raisePercent) {

        components.map.Map<String, Integer> accessed = map.newInstance();

        while (map.size() != 0) {
            Map.Pair<String, Integer> p = map.removeAny();
            int newSalary = p.value();
            String name = p.key();
            if (name.charAt(0) == initial) {
                newSalary = newSalary * (1 + raisePercent);
            }
            accessed.add(name, newSalary);
        }

        map.transferFrom(accessed);

    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires <pre>
     * [the salaries in map are positive]  and  raisePercent > 0  and
     * [the dynamic types of map and of all objects reachable from map
     *  (including any objects returned by operations (such as entrySet() and,
     *  from there, iterator()), and so on, recursively) support all
     *  optional operations]
     * </pre>
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    private static void giveRaise(java.util.Map<String, Integer> map,
            char initial, int raisePercent) {

        for (java.util.Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getKey().charAt(0) == initial) {
                int newSalary = entry.getValue() * (1 + raisePercent);
                map.put(entry.getKey(), newSalary);
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
