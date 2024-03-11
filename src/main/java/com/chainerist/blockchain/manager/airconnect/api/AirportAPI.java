package com.chainerist.blockchain.manager.airconnect.api;


import com.chainerist.blockchain.manager.airconnect.dto.AirportDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import okhttp3.Interceptor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.AIRPORT_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(AIRPORT_PATH)
public interface AirportAPI {

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE','OPERATOR')")
    @GetMapping("{iata}")
    public ChaineristResponse<Object> getByIata(@PathVariable("iata") String iata);

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ChaineristResponse<Object> save(@RequestBody AirportDTO airportDTO);

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT')")
    @PutMapping
    public ChaineristResponse<Object> update(@RequestBody AirportDTO airportDTO);

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE','OPERATOR')")
    @GetMapping
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE','OPERATOR')")
    @GetMapping("/search")
    public ChaineristResponse<Object> searchAirport(@RequestHeader Map<String, Object> header, @RequestParam(name = "pageNum", defaultValue = "0") int pageNum, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize);
}
