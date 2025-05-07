package com.ticket.repository;

import com.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatusAndCreatedDateBefore(String status, LocalDate date);
    List<Ticket> findByStatusAndResolvedDateBefore(String status, LocalDate date);
}