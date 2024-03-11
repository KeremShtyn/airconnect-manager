package com.chainerist.blockchain.manager.airconnect.api;


import com.chainerist.blockchain.manager.airconnect.dto.AircraftDTO;
import com.chainerist.blockchain.manager.airconnect.dto.SearchDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.AIRCRAFT_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(AIRCRAFT_PATH)
public interface AircraftAPI {

    @PreAuthorize("hasAnyAuthority('AIRLINE', 'ADMIN')")
    @GetMapping("{legNo}")
    public ChaineristResponse<Object> getBylegNo(@PathVariable("legNo") String legNo);

    @PreAuthorize("hasAnyAuthority('AIRLINE', 'ADMIN')")
    @PostMapping
    public ChaineristResponse<Object> save(@RequestBody AircraftDTO aircraftDTO);

    @PreAuthorize("hasAnyAuthority('AIRLINE', 'ADMIN')")
    @PutMapping
    public ChaineristResponse<Object> update(@RequestBody AircraftDTO aircraftDTO);

    @PreAuthorize("hasAnyAuthority('AIRLINE', 'ADMIN')")
    @GetMapping
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);

    @PreAuthorize("hasAnyAuthority('AIRLINE', 'ADMIN')")
    @GetMapping("/search")
    public ChaineristResponse<Object> searchAircraft(@RequestHeader Map<String, Object> header, @RequestParam(name = "pageNum", defaultValue = "0") int pageNum, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize);
}
