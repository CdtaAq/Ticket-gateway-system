package com.ticketinggateway.tickets.service;

import com.ticketinggateway.tickets.exception.TicketNotFoundException;
import com.ticketinggateway.tickets.model.Ticket;
import com.ticketinggateway.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TicketService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(String title, String description, String priority, String category, MultipartFile[] files) {
        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setPriority(priority);
        ticket.setCategory(category);
        ticket.setStatus("CREATED");

        Ticket savedTicket = ticketRepository.save(ticket);
        saveFiles(files, savedTicket.getId());

        return savedTicket;
    }

    private void saveFiles(MultipartFile[] files, Long ticketId) {
        Path uploadPath = Paths.get(UPLOAD_DIR + ticketId);
        try {
            Files.createDirectories(uploadPath);
            for (MultipartFile file : files) {
                Path filePath = uploadPath.resolve(file.getOriginalFilename());
                Files.write(filePath, file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ticket updateTicketStatus(Long id, String status) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }
}
