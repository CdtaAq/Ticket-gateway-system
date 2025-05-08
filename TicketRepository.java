package com.ticketinggateway.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ticketinggateway.tickets.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {}
