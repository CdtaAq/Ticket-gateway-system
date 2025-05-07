package com.example.ticketing.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TicketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private LocalDateTime timestamp;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private Employee performedBy;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public Employee getPerformedBy() { return performedBy; }
    public void setPerformedBy(Employee performedBy) { this.performedBy = performedBy; }
}
