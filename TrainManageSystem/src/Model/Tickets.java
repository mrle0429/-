package Model;

import java.util.Date;

public class Tickets {
    private String ticketId;
    private String userId;
    private String scheduled;
    private String seatNumber;
    private Date purchaseDate;
    private String comments;
    private String status;

    public Tickets(String ticketId, String userId, String scheduled, String seatNumber, Date purchaseDate, String comments, String status) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.scheduled = scheduled;
        this.seatNumber = seatNumber;
        this.purchaseDate = purchaseDate;
        this.comments = comments;
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
