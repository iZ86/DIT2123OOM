/** This class holds the data about the bag. */
public class Bag {
    /** bagID of the bag. */
    private String bagID;
    /** Weight of the bag. */
    private double bagWeight;
    /** Status of the bag screening. */
    private String screeningStatus;

    /** Sets bagID. */
    public void setBagID(String bagID) {
        this.bagID = bagID;
    }

    /** Returns the bagID. */
    public String getBagID() {
        return bagID;
    }

    /** Sets bagWeight. */
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
