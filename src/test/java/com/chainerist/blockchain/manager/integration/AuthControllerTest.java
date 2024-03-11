package com.chainerist.blockchain.manager.integration;

import com.chainerist.blockchain.manager.BlockchainManagerApplication;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlockchainManagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Tag("AuthControllerTest")
public class AuthControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get_AccessToken_OK() throws Exception {
        TokenRequestDTO tokenRequest=new TokenRequestDTO();
        tokenRequest.setUsername("system");
        tokenRequest.setPassword("syst3m");

        mockMvc.perform(post("/api/v1/auth/token")
                        .content(om.writeValueAsString(tokenRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload.accessToken").exists());
    }

    @Test
    public void get_AccessToken_Bad() throws Exception {
        TokenRequestDTO tokenRequest=new TokenRequestDTO();
        tokenRequest.setUsername("system");
        tokenRequest.setPassword("system");

        mockMvc.perform(post("/api/v1/auth/token")
                        .content(om.writeValueAsString(tokenRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.payload.accessToken").doesNotExist());
    }
}
