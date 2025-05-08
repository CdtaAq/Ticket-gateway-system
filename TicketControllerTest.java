package com.ticketinggateway.tickets;

import com.ticketinggateway.tickets.controller.TicketController;
import com.ticketinggateway.tickets.model.Ticket;
import com.ticketinggateway.tickets.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    public void testGetAllTickets() throws Exception {
        Ticket ticket = Ticket.builder().id(1L).title("Test Ticket").description("Test Description").build();
        when(ticketService.getAllTickets()).thenReturn(Collections.singletonList(ticket));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tickets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Ticket"));

        verify(ticketService, times(1)).getAllTickets();
    }
}
