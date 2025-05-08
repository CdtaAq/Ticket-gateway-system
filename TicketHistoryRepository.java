package com.ticketinggateway.tickets.repository;

import com.ticketinggateway.tickets.model.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {
    List<TicketHistory> findByTicketId(Long ticketId);
}
