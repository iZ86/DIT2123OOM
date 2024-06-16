import java.util.Random;

/** This class represents the utilities for this project,
 *  it will be used for getting random information.
 */
public class Utils {

    /** Number of data. */
    public static final int NUMBEROFDATA = 6;

    /** A constant array of bookingNumbers, used for randomized data. */
    public static final String[] BOOKINGNUMBERS = new String[]{
            "ABC123", "ABC124", "ABC125", "ABC223", "ABC323", "BAC123"
    };

    /** A constant array of seat numbers, used for randomized data. */
    public static final String[] SEATNUMBERS = new String[]{
            "A-01", "A-02", "A-03", "Y-29", "E-14", "O-44"
    };

    /** A constant array of boarding times, used for randomized data. */
    public static final String[] BOARDINGTIMES = new String[]{
            "00:20", "00:20", "00:20", "00:20", "18:00", "04:35"
    };


    /** A constant array of destinations that an airport can reach, used for randomized data. */
    public static final String[] DESTINATIONS = new String[]{
            "JHB", "JHB", "JHB", "JHB", "LAX", "SYD"
    };

    /** A constant array of staff ID's, used for randomized data. */
    public static final String[] STAFFID = new String[] {
            "S874",
            "S828",
            "S802",
            "S775",
            "S553",
            "S947",
            "S699",
            "S999",
            "S893",
            "S750",
    };

    /** A constant array of staff names, used for randomized data. */
    public static final String[] STAFFNAMES = new String[]{
            "Alice Johnson",
            "Bob Smith",
            "Charlie Davis",
            "Diana Evans",
            "Ethan Williams",
            "Fiona Brown",
            "George Miller",
            "Hannah Wilson",
            "Ian Moore",
            "Julia Taylor"
    };

    /** A constant array of staff roles, used for randomized data. */
    public static final String[] STAFFROLES = new String[]{
            "Senior Check-In Agent",
            "Check-In Agent",
            "Senior Check-In Agent",
            "Check-In Supervisor",
            "Senior Check-In Agent",
            "Check-In Agent",
            "Check-In Supervisor",
            "Check-In Agent",
            "Check-In Agent",
            "Check-In Agent"
    };

    /** A constant array of counters that user can check in physically, used for randomized data. */
    public static final int[] COUNTER = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};


    /** random object used to decide which random data should be used. */
    private Random random;


    /** Creates a new Utils object used for utilities in this project. */
    public Utils() {
        random = new Random();
    }

    /** Returns a staff object with random staff data. */
    public Staff getRandomStaff() {
        int index = Math.abs(random.nextInt()) % 10;
        String staffID = STAFFID[index];
        String staffName = STAFFNAMES[index];
        String staffRole = STAFFROLES[index];
        int counter = COUNTER[index];
        return new Staff(staffID, staffName, staffRole, counter);
    }

    /** Returns a random bag weight. */
    public double getRandomBagWeight() {
        return random.nextDouble(30.0);
    }

    // TODO: ...
    public int generateRandomGateNumber() {
        return 0;
    }

}
