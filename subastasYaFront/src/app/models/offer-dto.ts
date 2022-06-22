export class OfferDTO {
    bidderUserName: string;
    announcementId: number;
    offerValue: number;

    constructor(bidderUserName: string, announcement: number, offerValue: number) {
        this.bidderUserName = bidderUserName;
        this.announcementId = announcement;
        this.offerValue = offerValue;
    }
}
