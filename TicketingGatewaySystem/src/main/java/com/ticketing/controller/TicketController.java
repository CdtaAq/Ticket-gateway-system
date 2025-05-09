package com.ticketing.controller;

import com.ticketing.entity.Ticket;
import com.ticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
        return "redirect:/tickets/create";
    }
}
