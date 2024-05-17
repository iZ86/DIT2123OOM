import java.util.Random;

/** This class represents the utilities for this project,
 *  it will be used for getting random information.
 */
public class Utils {
    /** A constant array of bookingID's. */
    public static final String[] BOOKINGIDS = new String[]{
            "ABC123", "ABC124", "ABC125", "ABC223", "ABC323", "BAC123"
    };

    /** A constant array of plane seats. */
    public static final String[] PLANESEATS = new String[]{
            "A-01", "A-02", "A-03", "Y-29", "E-14", "O-44"
    };

    /** A constant array of boarding times. */
    // TODO: Maybe change the format.
    public static final String[] BOARDINGTIMES = new String[]{
            "00:20", "00:20", "00:20", "00:20", "18:00", "04:35"
    };


    /** A constant array of destinations that an airport can reach. */
    public static final String[] DESTINATIONS = new String[]{
            "JHB", "JHB", "JHB", "JHB", "LAX", "SYD"
    };


    /** random object used to decide which data should be used. */
    private Random random;


}
