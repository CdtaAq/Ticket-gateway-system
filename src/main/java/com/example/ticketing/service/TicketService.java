package com.example.ticketing.service;

import com.example.ticketing.entity.*;
import com.example.ticketing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepo;

    @Autowired
    private TicketHistoryRepository historyRepo;

    public Ticket createTicket(Ticket ticket, MultipartFile file, Employee creator) throws IOException {
        if (file != null && !file.isEmpty()) {
            String path = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
            ticket.setAttachmentPath(path);
        }

        ticket.setStatus("CREATED");
        ticket.setCreatedBy(creator);
        Ticket savedTicket = ticketRepo.save(ticket);
        addHistory(savedTicket, "CREATED", creator);
        return savedTicket;
    }

    public Ticket updateStatus(Long id, String action, Employee performer) {
        Ticket ticket = ticketRepo.findById(id).orElseThrow();
        ticket.setStatus(action);
        Ticket updated = ticketRepo.save(ticket);
        addHistory(updated, action, performer);
        return updated;
    }

    private void addHistory(Ticket ticket, String action, Employee by) {
        TicketHistory history = new TicketHistory();
        history.setTicket(ticket);
        history.setAction(action);
        history.setTimestamp(LocalDateTime.now());
        history.setPerformedBy(by);
        historyRepo.save(history);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public List<TicketHistory> getHistory(Long ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId).orElseThrow();
        return ticket.getHistory();
    }
}
