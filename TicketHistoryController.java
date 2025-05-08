package com.ticketinggateway.tickets.controller;

import com.ticketinggateway.tickets.model.TicketHistory;
import com.ticketinggateway.tickets.repository.TicketHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets/history")
public class TicketHistoryController {

    @Autowired
    private TicketHistoryRepository ticketHistoryRepository;

    @GetMapping("/{ticketId}")
    public List<TicketHistory> getTicketHistory(@PathVariable Long ticketId) {
        return ticketHistoryRepository.findByTicketId(ticketId);
    }
}
