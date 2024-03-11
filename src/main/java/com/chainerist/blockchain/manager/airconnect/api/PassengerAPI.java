package com.chainerist.blockchain.manager.airconnect.api;


import com.chainerist.blockchain.manager.airconnect.dto.PassengerDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.AUTH_PATH;
import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.PASSENGER_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(PASSENGER_PATH)
public interface PassengerAPI {


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ChaineristResponse<Object> save(@RequestBody PassengerDTO passengerDTO);

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PutMapping
    public ChaineristResponse<Object> update(@RequestBody PassengerDTO passengerDTO);

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("{id}")
    public ChaineristResponse<Object> getById(@PathVariable("id") String id);

    @PreAuthorize("hasAnyAuthority('USER','AIRPORT','AIRLINE','ADMIN')")
    @GetMapping
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);

    @PreAuthorize("hasAnyAuthority('USER','AIRPORT','AIRLINE','ADMIN')")
    @GetMapping("/getByFirstName/{firstName}")
    public ChaineristResponse<Object> getByFirstName(@PathVariable("firstName") String firstName);

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/search")
    public ChaineristResponse<Object> searchPassenger(@RequestHeader Map<String, Object> header, @RequestParam(name = "pageNum", defaultValue = "0") int pageNum, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize);
}
