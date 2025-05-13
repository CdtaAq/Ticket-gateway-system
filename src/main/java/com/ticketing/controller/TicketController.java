package com.ticketing.controller;

import com.ticketing.entity.Ticket;
import com.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Retrieve ticket details by ticketId
     */
    @GetMapping("/details/{ticketId}")
    public Optional<Ticket> getTicketDetails(@PathVariable Long ticketId) {
        return ticketRepository.findById(ticketId);
    }

    /**
     * Endpoint to redirect to the ticket details using QR code link
     */
    @GetMapping("/view/{ticketId}")
    public RedirectView viewTicket(@PathVariable Long ticketId) {
        String redirectUrl = "/ticket/details/" + ticketId;
        return new RedirectView(redirectUrl);
    }
}
