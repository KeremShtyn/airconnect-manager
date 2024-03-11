package com.chainerist.blockchain.manager.airconnect.api;


import com.chainerist.blockchain.manager.airconnect.dto.PassengerDTO;
import com.chainerist.blockchain.manager.airconnect.dto.StationDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.STATION_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(STATION_PATH)
public interface StationAPI {


    @PreAuthorize("hasAnyAuthority('AIRPORT', 'ADMIN')")
    @PostMapping
    public ChaineristResponse<Object> save(@RequestBody StationDTO stationDTO);

    @PreAuthorize("hasAnyAuthority('AIRPORT', 'ADMIN')")
    @PutMapping
    public ChaineristResponse<Object> update(@RequestBody StationDTO stationDTO);

    @PreAuthorize("hasAnyAuthority('AIRPORT', 'ADMIN')")
    @GetMapping("{id}")
    public ChaineristResponse<Object> getById(@PathVariable("id") String id);

    @PreAuthorize("hasAnyAuthority('AIRPORT', 'ADMIN')")
    @GetMapping
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);


}
