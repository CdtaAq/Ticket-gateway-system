package com.ticketing.service;

import com.ticketing.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public int countAllTickets() {
        return (int) ticketRepository.count();
    }

    public int countTicketsByStatus(String status) {
        return ticketRepository.countByStatus(status);
    }
}
