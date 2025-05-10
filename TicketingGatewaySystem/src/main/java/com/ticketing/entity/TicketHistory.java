package com.ticketing.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class TicketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ticket ticket;

    private String action;
    private String actionBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actionDate = new Date();

    private String comments;

    // Default Constructor
    public TicketHistory() {
    }

    // Parameterized Constructor
    public TicketHistory(Ticket ticket, String action, String actionBy, String comments) {
        this.ticket = ticket;
        this.action = action;
        this.actionBy = actionBy;
        this.comments = comments;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
