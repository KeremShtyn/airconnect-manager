package com.chainerist.blockchain.manager.airconnect.api;


import com.chainerist.blockchain.manager.airconnect.dto.GateDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.GATE_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(GATE_PATH)
public interface GateAPI {

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE')")
    @GetMapping("{id}")
    public ChaineristResponse<Object> getById(@PathVariable("id") String id);

    @PreAuthorize("hasAnyAuthority('AIRPORT', 'ADMIN')")
    @PostMapping
    public ChaineristResponse<Object> save(@RequestBody GateDTO gateDTO);

    @PreAuthorize("hasAnyAuthority('AIRPORT', 'ADMIN')")
    @PutMapping
    public ChaineristResponse<Object> update(@RequestBody GateDTO gateDTO);

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE')")
    @GetMapping
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);

}
