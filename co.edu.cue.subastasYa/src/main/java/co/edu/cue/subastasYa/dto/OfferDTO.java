package co.edu.cue.subastasYa.dto;

public class OfferDTO {
    private String bidderUserName;
    private int announcementId;
    private double offerValue;

    public OfferDTO() {
    }

    public String getBidderUserName() {
        return bidderUserName;
    }

    public void setBidderUserName(String bidderUserName) {
        this.bidderUserName = bidderUserName;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    public double getOfferValue() {
        return offerValue;
    }

    public void setOfferValue(double offerValue) {
        this.offerValue = offerValue;
    }
}
