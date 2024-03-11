package com.chainerist.blockchain.manager.integration;

import com.chainerist.blockchain.manager.BlockchainManagerApplication;
import com.chainerist.blockchain.manager.airconnect.domain.Aircraft;
import com.chainerist.blockchain.manager.airconnect.domain.Airline;
import com.chainerist.blockchain.manager.airconnect.dto.AircraftDTO;
import com.chainerist.blockchain.manager.airconnect.dto.AirlineDTO;
import com.chainerist.blockchain.manager.airconnect.entity.AirlineEntity;
import com.chainerist.blockchain.manager.authentication.dto.TokenRequestDTO;
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

import javax.servlet.http.HttpServletResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlockchainManagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Tag("AircraftControllerTest")
public class AircraftControllerTest {

    private static final ObjectMapper om = new ObjectMapper();



    @Autowired
    private MockMvc mockMvc;

    private AirlineDTO airline;

    @Test
    @WithMockUser("spring")
    public void get_SetAircraft_OK() throws Exception {
        AircraftDTO aircraftDTO= new AircraftDTO();
        aircraftDTO.setLegNo("1234");
        aircraftDTO.setType("Type");
        aircraftDTO.setAirlineId("8eff4e73-c2db-4dab-b396-936e7cd3e962");
        aircraftDTO.setModel("Model");
        aircraftDTO.setLuggageCapacity(100);
        aircraftDTO.setPassengerCapacity(250);



        mockMvc.perform(post("/api/v1/aircrafts")
                        .content(om.writeValueAsString(aircraftDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @WithMockUser("spring")
    public void get_SetAircraft_Bad() throws Exception {
        AircraftDTO aircraftDTO= new AircraftDTO();
        aircraftDTO.setLegNo("");
        aircraftDTO.setType("Type");
        aircraftDTO.setAirlineId("8eff4e73-c2db-4dab-b396-936e7cd3e962");
        aircraftDTO.setModel("Model");
        aircraftDTO.setLuggageCapacity(100);
        aircraftDTO.setPassengerCapacity(250);

        mockMvc.perform(post("/api/v1/aircrafts")
                        .content(om.writeValueAsString(aircraftDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
    }
}


