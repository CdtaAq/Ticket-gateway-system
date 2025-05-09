package com.ticketing.controller;

import com.ticketing.entity.Ticket;
import com.ticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/create")
    public String showTicketForm() {
        return "ticket-create";
    }

    @PostMapping("/create")
    public String createTicket(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("priority") String priority,
            @RequestParam("category") String category,
            @RequestParam("file") MultipartFile file,
            Model model) {

        Ticket ticket = new Ticket(title, description, priority, "OPEN", category, null);
        ticketService.createTicket(ticket, file);

        model.addAttribute("message", "Ticket created successfully!");
        return "redirect:/tickets/list";
    }

    @GetMapping("/list")
    public String listTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "ticket-list";
    }

    @PostMapping("/approve")
    public String approveTicket(@RequestParam("ticketId") Long ticketId) {
        ticketService.updateTicketStatus(ticketId, "APPROVED");
        return "redirect:/tickets/list";
    }

    @PostMapping("/reject")
    public String rejectTicket(@RequestParam("ticketId") Long ticketId) {
        ticketService.updateTicketStatus(ticketId, "REJECTED");
        return "redirect:/tickets/list";
    }
}
