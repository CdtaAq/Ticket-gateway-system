package com.example.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ticketing.entity.TicketHistory;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {}
