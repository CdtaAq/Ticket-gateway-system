package com.ticketing.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String priority;
    private String status;
    private String category;
    private String fileAttachmentPath;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    // Constructors, Getters, Setters

    public Ticket() {
    }

    public Ticket(String title, String description, String priority, String status, String category, String fileAttachmentPath) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.fileAttachmentPath = fileAttachmentPath;
    }

    // Getters and Setters
    // ...
}
