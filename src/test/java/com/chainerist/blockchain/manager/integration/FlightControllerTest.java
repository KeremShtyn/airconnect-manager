package com.chainerist.blockchain.manager.integration;


import com.chainerist.blockchain.manager.BlockchainManagerApplication;
import com.chainerist.blockchain.manager.airconnect.dto.AircraftDTO;
import com.chainerist.blockchain.manager.airconnect.dto.AirlineDTO;
import com.chainerist.blockchain.manager.airconnect.dto.FlightDTO;
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
@Tag("FlightControllerTest")
public class FlightControllerTest {


    private static final ObjectMapper om = new ObjectMapper();



    @Autowired
    private MockMvc mockMvc;

    private AirlineDTO airline;

    @Test
    @WithMockUser("spring")
    public void get_SetFlight_OK() throws Exception {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNo(1);
        flightDTO.setStatus(FlightStatus.CANCELLED);
        flightDTO.setAirlineId("ce0fcf6d-2276-470a-82c5-467ef9f7b5dc");


        mockMvc.perform(post("/api/v1/flights")
                        .content(om.writeValueAsString(flightDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("spring")
    public void get_SetFlight_Bad() throws Exception {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNo(null);
        flightDTO.setStatus(FlightStatus.ARRIVED);
        flightDTO.setAirlineId("ce0fcf6d-2276-470a-82c5-467ef9f7b5dc");

        mockMvc.perform(post("/api/v1/flights")
                        .content(om.writeValueAsString(flightDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
