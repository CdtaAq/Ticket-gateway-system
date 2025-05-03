package com.example.ticketing.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String description;
    private String status; // CREATED, APPROVED, etc.
    private String attachmentPath;

    @ManyToOne
    private Employee createdBy;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<TicketHistory> history = new ArrayList<>();

    // Getters and setters
}
