/** This class holds the data about the bag. */
public class Bag {
    /** bagID of the bag. */
    private String bagID;
    /** Color of the bag. */
    private String bagColor;
    /** Weight of the bag. */
    private double bagWeight;

    /** Creates a bag object to hold data about a bag. */
    public Bag(String bagID, String bagColor, double bagWeight) {
        this.bagID = bagID;
        this.bagColor = bagColor;
        this.bagWeight = bagWeight;
    }

    /** Returns the bagID. */
    public String getBagID() {
        return bagID;
    }

    /** Returns the color of the bag. */
    public String getBagColor() {
        return bagColor;
    }

    /** Returns the weight of the bag. */
    public double getBagWeight() {
        return bagWeight;
    }
}
