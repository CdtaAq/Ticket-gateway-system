package com.ticketinggateway.tickets.controller;

import com.ticketinggateway.tickets.model.Ticket;
import com.ticketinggateway.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("priority") String priority,
            @RequestParam("category") String category,
            @RequestParam("files") MultipartFile[] files) {

        Ticket ticket = ticketService.createTicket(title, description, priority, category, files);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<Ticket> approveTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.updateTicketStatus(id, "APPROVED");
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<Ticket> rejectTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.updateTicketStatus(id, "REJECTED");
        return ResponseEntity.ok(ticket);
    }
}
