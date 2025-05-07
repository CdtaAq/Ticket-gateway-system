package com.ticket.scheduler;

import com.ticket.model.Ticket;
import com.ticket.repository.TicketRepository;
import com.ticket.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TicketScheduler {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkPendingTickets() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        List<Ticket> pendingTickets = ticketRepository.findByStatusAndCreatedDateBefore("PENDING", sevenDaysAgo);
        for (Ticket ticket : pendingTickets) {
            emailService.sendSimpleEmail(
                ticket.getManagerEmail(),
                "Ticket Reminder: Pending too long",
                "Ticket " + ticket.getId() + " is pending for over 7 days."
            );
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void autoCloseResolvedTickets() {
        LocalDate fiveDaysAgo = LocalDate.now().minusDays(5);
        List<Ticket> resolvedTickets = ticketRepository.findByStatusAndResolvedDateBefore("RESOLVED", fiveDaysAgo);
        for (Ticket ticket : resolvedTickets) {
            ticket.setStatus("CLOSED");
            ticketRepository.save(ticket);
            emailService.sendSimpleEmail(
                ticket.getEmployeeEmail(),
                "Ticket Closed Automatically",
                "Your resolved ticket " + ticket.getId() + " has been closed automatically."
            );
        }
    }
}