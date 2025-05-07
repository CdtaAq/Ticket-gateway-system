package com.example.ticketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ticketing.entity.Ticket;
import com.example.ticketing.entity.TicketHistory;
import com.example.ticketing.repository.TicketRepository;
import com.example.ticketing.repository.TicketHistoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepo;

    @Autowired
    private TicketHistoryRepository historyRepo;

    public Ticket createTicket(Ticket ticket) {
        ticket.setStatus("CREATED");
        ticket.setCreatedDate(LocalDateTime.now());
        ticket.setUpdatedDate(LocalDateTime.now());
        Ticket savedTicket = ticketRepo.save(ticket);
        addHistory(savedTicket, "CREATED");
        return savedTicket;
    }

    public Ticket updateStatus(Long id, String status) {
        Ticket ticket = ticketRepo.findById(id).orElseThrow();
        ticket.setStatus(status);
        ticket.setUpdatedDate(LocalDateTime.now());
        Ticket updated = ticketRepo.save(ticket);
        addHistory(updated, status);
        return updated;
    }

    private void addHistory(Ticket ticket, String action) {
        TicketHistory history = new TicketHistory();
        history.setTicket(ticket);
        history.setAction(action);
        history.setTimestamp(LocalDateTime.now());
        historyRepo.save(history);
    }

    public List<TicketHistory> getHistory(Long ticketId) {
        return historyRepo.findAll();
    }
}
