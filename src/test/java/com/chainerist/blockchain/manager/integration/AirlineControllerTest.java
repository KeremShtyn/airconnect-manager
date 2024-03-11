package com.chainerist.blockchain.manager.integration;


import com.chainerist.blockchain.manager.BlockchainManagerApplication;
import com.chainerist.blockchain.manager.airconnect.dto.AircraftDTO;
import com.chainerist.blockchain.manager.airconnect.dto.AirlineDTO;
import com.chainerist.blockchain.manager.airconnect.dto.FlightDTO;
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

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlockchainManagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Tag("AirlineControllerTest")
public class AirlineControllerTest {

    private static final ObjectMapper om = new ObjectMapper();



    @Autowired
    private MockMvc mockMvc;

    private FlightDTO flights;


    @Test
    @WithMockUser("spring")
    public void get_SetAirline_OK() throws Exception {
        AirlineDTO airlineDTO = new AirlineDTO();
        airlineDTO.setIata("THY");
        airlineDTO.setIcao("TK");
        airlineDTO.setName("Turk Hava Yollari");
        airlineDTO.setCountry("Turkey");
        airlineDTO.setCallsign("Turkey");



        mockMvc.perform(post("/api/v1/airlines")
                        .content(om.writeValueAsString(airlineDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("spring")
    public void get_SetAirline_Bad() throws Exception {
        AirlineDTO airlineDTO = new AirlineDTO();
        airlineDTO.setIata("");
        airlineDTO.setIcao("");
        airlineDTO.setName("Turk Hava Yollari");
        airlineDTO.setCountry("");
        airlineDTO.setCallsign("Turkey");

        mockMvc.perform(post("/api/v1/airlines")
                        .content(om.writeValueAsString(airlineDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
