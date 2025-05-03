package com.example.ticketing.controller;

import com.example.ticketing.entity.Ticket;
import com.example.ticketing.entity.Employee;
import com.example.ticketing.entity.TicketHistory;
import com.example.ticketing.service.TicketService;
import com.example.ticketing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(
        @RequestParam String subject,
        @RequestParam String description,
        @RequestParam(required = false) MultipartFile attachment,
        Principal principal) {

        Employee creator = employeeService.findByUsername(principal.getName());
        Ticket ticket = new Ticket();
        ticket.setSubject(subject);
        ticket.setDescription(description);

        try {
            return ResponseEntity.ok(ticketService.createTicket(ticket, attachment, creator));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<Ticket> updateStatus(
        @PathVariable Long id,
        @RequestParam String action,
        Principal principal) {

        Employee performer = employeeService.findByUsername(principal.getName());
        return ResponseEntity.ok(ticketService.updateStatus(id, action, performer));
    }

    @GetMapping
    public List<Ticket> listTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}/history")
    public List<TicketHistory> ticketHistory(@PathVariable Long id) {
        return ticketService.getHistory(id);
    }
}
