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

    // Constructors, Getters, Setters
    public TicketHistory() {
    }

    public TicketHistory(Ticket ticket, String action, String actionBy, String comments) {
        this.ticket = ticket;
        this.action = action;
        this.actionBy = actionBy;
        this.comments = comments;
    }

    // Getters and Setters
    // ...
}
