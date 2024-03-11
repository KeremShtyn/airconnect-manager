package com.chainerist.blockchain.manager.integration;


import com.chainerist.blockchain.manager.BlockchainManagerApplication;
import com.chainerist.blockchain.manager.airconnect.dto.AircraftDTO;
import com.chainerist.blockchain.manager.airconnect.dto.AirlineDTO;
import com.chainerist.blockchain.manager.airconnect.dto.AirportDTO;
import com.chainerist.blockchain.manager.airconnect.dto.OperatorDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlockchainManagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Tag("AirportControllerTest")
public class AirportControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    private OperatorDTO operator;

    @Test
    @WithMockUser("spring")
    public void get_SetAirport_OK() throws Exception {
        AirportDTO airportDTO= new AirportDTO();
        airportDTO.setIata("TK");
        airportDTO.setIcao("LB");
        airportDTO.setCity("Istanbul");
        airportDTO.setName("Istanbul Ataturk Airport");
        airportDTO.setCountry("Turkey");
        airportDTO.setOperator(operator);

        mockMvc.perform(post("/api/v1/airports")
                        .content(om.writeValueAsString(airportDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
}

    @Test
    @WithMockUser("spring")
    public void get_SetAirport_Bad() throws Exception {
        AirportDTO airportDTO= new AirportDTO();
        airportDTO.setIata("");
        airportDTO.setIcao("LB");
        airportDTO.setCity("Istanbul");
        airportDTO.setName("Istanbul Ataturk Airport");
        airportDTO.setCountry("Turkey");
        airportDTO.setOperator(operator);

        mockMvc.perform(post("/api/v1/airports")
                        .content(om.writeValueAsString(airportDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
