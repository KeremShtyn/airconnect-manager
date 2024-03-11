package com.chainerist.blockchain.manager.airconnect.api;

import com.chainerist.blockchain.manager.airconnect.dto.AirlineDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.AIRLINE_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(AIRLINE_PATH)
public interface AirlineAPI {

    @PreAuthorize("hasAnyAuthority('AIRLINE','AIRPORT', 'ADMIN')")
    @GetMapping("{iata}")
    public ChaineristResponse<Object> getByIata(@PathVariable("iata") String iata);

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ChaineristResponse<Object> save(@RequestBody AirlineDTO airlineDTO);

    @PreAuthorize("hasAnyAuthority('AIRLINE', 'ADMIN')")
    @PutMapping
    public ChaineristResponse<Object> update(@RequestBody AirlineDTO airlineDTO);

    @PreAuthorize("hasAnyAuthority('AIRLINE','AIRPORT', 'ADMIN')")
    @GetMapping
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);

    @PreAuthorize("hasAnyAuthority('AIRLINE','AIRPORT', 'ADMIN')")
    @GetMapping("/search")
    public ChaineristResponse<Object> searchAirline(@RequestHeader Map<String, Object> header, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size);

}
