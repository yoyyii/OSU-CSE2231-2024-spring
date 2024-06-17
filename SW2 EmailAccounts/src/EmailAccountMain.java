import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
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
        EmailAccount myAccount = new EmailAccount1("Brutus", "Buckeye");
        /*
         * Should print: Brutus Buckeye
         */
        out.println(myAccount.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(myAccount.emailAddress());
        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(myAccount);

        Sequence<EmailAccount> allEmail = new Sequence1L<>();

        allEmail.add(0, myAccount);

        String name;
        out.print("pleas enter first and last name: ");
        name = in.nextLine();

        while (name != "") {
            String first = name.substring(0, name.indexOf(" "));
            String last = name.substring(name.indexOf(" ") + 1, name.length());

            EmailAccount account = new EmailAccount1(first, last);

            allEmail.add(allEmail.length(), account);

            for (int i = allEmail.length() - 1; i >= 0; i--) {
                out.println(allEmail.entry(i));
            }

            out.print("pleas enter first and last name: ");
            name = in.nextLine();
        }

        in.close();
        out.close();
    }

}
