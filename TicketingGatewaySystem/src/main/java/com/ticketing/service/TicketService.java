package com.ticketing.service;

import com.ticketing.entity.Ticket;
import com.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public Ticket createTicket(Ticket ticket, MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String filePath = saveFile(file);
                ticket.setFileAttachmentPath(filePath);
            }
            return ticketRepository.save(ticket);
        } catch (IOException e) {
            throw new RuntimeException("File upload error: " + e.getMessage());
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filePath;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
