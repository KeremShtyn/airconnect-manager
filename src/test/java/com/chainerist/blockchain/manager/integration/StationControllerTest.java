package com.chainerist.blockchain.manager.integration;


import com.chainerist.blockchain.manager.BlockchainManagerApplication;
import com.chainerist.blockchain.manager.airconnect.domain.Flight;
import com.chainerist.blockchain.manager.airconnect.dto.*;
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
@Tag("StationControllerTest")
public class StationControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    private OperatorDTO operator;
    private FlightDTO flightDTO;

    @Test
    @WithMockUser("spring")
    public void get_SetStation_OK() throws Exception {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setStationNo("12");
        stationDTO.setFlightId("a94ccd04-92e3-48f7-a4f2-a7a9ef0a6885");
        stationDTO.setDestAirportId("c5ede6f2-9b0b-4446-95a4-f235ea306464");


        mockMvc.perform(post("/api/v1/stations")
                        .content(om.writeValueAsString(stationDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("spring")
    public void get_SetStation_Bad() throws Exception {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setStationNo("");
        stationDTO.setFlightId("a94ccd04-92e3-48f7-a4f2-a7a9ef0a6885");
        stationDTO.setDestAirportId("c5ede6f2-9b0b-4446-95a4-f235ea306464");

        mockMvc.perform(post("/api/v1/stations")
                        .content(om.writeValueAsString(stationDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
