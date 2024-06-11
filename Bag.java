/** This class holds the data about the bag. */
public class Bag {
    /** bagID of the bag. */
    private String bagID;
    /** Weight of the bag. */
    private double bagWeight;
    /** Status of the bag screening. */
    private String screeningStatus;

    /** Creates a bag object to hold data about a bag. */
    public Bag(String bagID, double bagWeight, String screeningStatus) {
        this.bagID = bagID;
        this.bagWeight = bagWeight;
        this.screeningStatus = screeningStatus;
    }

    public void setBagID(String bagID) {
        this.bagID = bagID;
    }

    /** Returns the bagID. */
    public String getBagID() {
        return bagID;
    }

    public void setBagWeight(double bagWeight) {
        this.bagWeight = bagWeight;
    }

    /** Returns the weight of the bag. */
    public double getBagWeight() {
        return bagWeight;
    }

    /** sets ScreeningStatus. */
    public void setScreeningStatus(String screeningStatus) {
        this.screeningStatus = screeningStatus;
    }

    /** Return screeningStatus. */
    public String getScreeningStatus() {
        return screeningStatus;
    }
}
