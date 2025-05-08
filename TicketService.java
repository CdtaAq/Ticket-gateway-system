package com.ticketinggateway.tickets.service;

import com.ticketinggateway.tickets.exception.TicketNotFoundException;
import com.ticketinggateway.tickets.model.Ticket;
import com.ticketinggateway.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(String title, String description, String priority, String category, MultipartFile[] files) {
        Ticket ticket = Ticket.builder()
                .title(title)
                .description(description)
                .priority(priority)
                .category(category)
                .build();
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
