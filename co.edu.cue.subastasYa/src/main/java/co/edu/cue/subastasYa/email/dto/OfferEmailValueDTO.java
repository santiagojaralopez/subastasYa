package co.edu.cue.subastasYa.email.dto;

public class OfferEmailValueDTO {
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String userName;
    private String publicationName;
    private String bidderUserName;
    private double value;

    public OfferEmailValueDTO() {
    }

    public OfferEmailValueDTO(String mailFrom, String mailTo, String subject, String userName, String publicationName, String bidderUserName, double value) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.userName = userName;
        this.publicationName = publicationName;
        this.bidderUserName = bidderUserName;
        this.value = value;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public String getBidderUserName() {
        return bidderUserName;
    }

    public void setBidderUserName(String bidderUserName) {
        this.bidderUserName = bidderUserName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
