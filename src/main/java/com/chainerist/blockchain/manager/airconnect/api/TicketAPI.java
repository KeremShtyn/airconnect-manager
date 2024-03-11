package com.chainerist.blockchain.manager.airconnect.api;


import com.chainerist.blockchain.manager.airconnect.dto.TicketDTO;
import com.chainerist.blockchain.manager.airconnect.dto.SearchDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.TICKET_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(TICKET_PATH)
public interface TicketAPI {


    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping
    public ChaineristResponse<Object> save(@RequestBody TicketDTO ticketDTO);

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping
    public ChaineristResponse<Object> update(@RequestBody TicketDTO ticketDTO);

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("{id}")
    public ChaineristResponse<Object> getById(@PathVariable("id") String id);

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/search")
    public ChaineristResponse<Object> searchTicket(@RequestHeader Map<String, Object> header, @RequestParam(name = "pageNum", defaultValue = "0") int pageNum, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize);
}
