package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Tickets {
    private String ticketId;
    private String userId;
    private String scheduled;
    private String seatNumber;
    private LocalDateTime purchaseDate;
    private String comments;
    private String status;

    public Tickets(String ticketId, String userId, String scheduled, String seatNumber, LocalDateTime purchaseDate, String comments, String status) {
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

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
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
