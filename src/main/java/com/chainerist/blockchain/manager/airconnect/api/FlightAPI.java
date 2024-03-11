package com.chainerist.blockchain.manager.airconnect.api;


import com.chainerist.blockchain.manager.airconnect.dto.AirportDTO;
import com.chainerist.blockchain.manager.airconnect.dto.FlightDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.FLIGHT_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(FLIGHT_PATH)
public interface FlightAPI {

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE','USER')")
    @GetMapping("{id}")
    public ChaineristResponse<Object> getById(@PathVariable("id") String id);

    @PreAuthorize("hasAnyAuthority('AIRLINE','ADMIN')")
    @PostMapping
    public ChaineristResponse<Object> save(@RequestBody FlightDTO flightDTO);

    @PreAuthorize("hasAnyAuthority('AIRLINE','ADMIN')")
    @PutMapping
    public ChaineristResponse<Object> update(@RequestBody FlightDTO flightDTO);

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE','USER')")
    @GetMapping
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE','USER')")
    @GetMapping("/search")
    public ChaineristResponse<Object> searchFlight(@RequestHeader Map<String, Object> header, @RequestParam(name = "pageNum", defaultValue = "0") int pageNum, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize);

}
