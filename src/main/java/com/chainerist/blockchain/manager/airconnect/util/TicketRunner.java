/*package com.chainerist.blockchain.manager.airconnect.util;



import com.chainerist.blockchain.manager.airconnect.domain.Passenger;
import com.chainerist.blockchain.manager.airconnect.domain.Ticket;
import com.chainerist.blockchain.manager.airconnect.entity.PassengerEntity;
import com.chainerist.blockchain.manager.airconnect.entity.TicketEntity;
import com.chainerist.blockchain.manager.airconnect.service.PassengerService;
import com.chainerist.blockchain.manager.airconnect.service.TicketService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
public class TicketRunner implements CommandLineRunner {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerService passengerService;



    @Override
    public void run(String... args) throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/passenger.json");
            log.info("Saving Passenger data");
            List<Passenger> passenger = objectMapper.readValue(inputStream, new TypeReference<List<Passenger>>() {});
            passenger.stream().forEach(x -> passengerService.createPassenger(x));
            log.info("Successfully save");

            inputStream = TypeReference.class.getResourceAsStream("/json/ticket.json");
            log.info("Saving Ticket data");
            List<Ticket> ticketList = objectMapper.readValue(inputStream, new TypeReference<List<Ticket>>() {});
            ticketList.stream().forEach(x -> ticketService.createTicket(x));
            log.info("Successfully save");


        }
        catch(IOException e){
            log.error("Unable to save data" + e.getMessage());
        }
    };

}*/
