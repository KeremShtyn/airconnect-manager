package com.chainerist.blockchain.manager.integration;


import com.chainerist.blockchain.manager.BlockchainManagerApplication;
import com.chainerist.blockchain.manager.airconnect.dto.AirportDTO;
import com.chainerist.blockchain.manager.airconnect.dto.OperatorDTO;
import com.chainerist.blockchain.manager.airconnect.dto.PassengerDTO;
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
@Tag("PassengerControllerTest")
public class PassengerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    private OperatorDTO operator;

    @Test
    @WithMockUser("spring")
    public void get_SetPassenger_OK() throws Exception {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setIdentifier("19");
        passengerDTO.setFirstName("Caglayan");
        passengerDTO.setLastName("Sancaktar");


        mockMvc.perform(post("/api/v1/passengers")
                        .content(om.writeValueAsString(passengerDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("spring")
    public void get_SetPassenger_Bad() throws Exception {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setIdentifier("");
        passengerDTO.setFirstName("Kerem");
        passengerDTO.setLastName("Sahtiyan");

        mockMvc.perform(post("/api/v1/passengers")
                        .content(om.writeValueAsString(passengerDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
