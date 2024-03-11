package com.chainerist.blockchain.manager.integration;


import com.chainerist.blockchain.manager.BlockchainManagerApplication;
import com.chainerist.blockchain.manager.airconnect.dto.AirlineDTO;
import com.chainerist.blockchain.manager.airconnect.dto.FlightDTO;
import com.chainerist.blockchain.manager.airconnect.dto.TicketDTO;
import com.chainerist.blockchain.manager.airconnect.enums.FlightStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlockchainManagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Tag("TicketControllerTest")
public class TicketControllerTest {

    private static final ObjectMapper om = new ObjectMapper();



    @Autowired
    private MockMvc mockMvc;

    private AirlineDTO airline;

    @Test
    @WithMockUser("spring")
    public void get_SetTicket_OK() throws Exception {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setIdentifier("123");
        ticketDTO.setSeat("123");
        ticketDTO.setFlightId("a94ccd04-92e3-48f7-a4f2-a7a9ef0a6885");
        ticketDTO.setGateId("84513244-2d0b-4156-a21c-3c3697d54e99");
        ticketDTO.setPassengerId("839f2e82-8669-45e8-8fd5-672debf32ea1");



        mockMvc.perform(post("/api/v1/tickets")
                        .content(om.writeValueAsString(ticketDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("spring")
    public void get_SetTicket_Bad() throws Exception {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setIdentifier("");
        ticketDTO.setSeat("123");

        mockMvc.perform(post("/api/v1/tickets")
                        .content(om.writeValueAsString(ticketDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
