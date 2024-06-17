import components.map.Map;
import components.map.Map1L;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Put your name here
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    // TODO - declare static and instance data members

    private String firstName;
    private String lastName;
    private String email;
    Map<String, Integer> uniqueNum = new Map1L<>();

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {

        // TODO - fill in body

        this.firstName = firstName;
        this.lastName = lastName;

        String lowerLast = lastName.toLowerCase();
        if (this.uniqueNum.hasKey(lowerLast)) {
            this.uniqueNum.replaceValue(lowerLast,
                    this.uniqueNum.value(lowerLast) + 1);
        } else {
            this.uniqueNum.add(lowerLast, 1);

        }
        this.email = lowerLast + "."
                + Integer.toString(this.uniqueNum.value(lowerLast))
                + "@osu.edu";

    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {

        // TODO - fill in body

        return this.firstName + " " + this.lastName;

    }

    @Override
    public String emailAddress() {

        // TODO - fill in body

        return this.email;

    }

    @Override
    public String toString() {

        // TODO - fill in body
        return "Name: " + this.firstName + " " + this.lastName + ", Email: "
                + this.email;

    }

}