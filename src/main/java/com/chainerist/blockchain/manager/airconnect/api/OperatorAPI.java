package com.chainerist.blockchain.manager.airconnect.api;


import com.chainerist.blockchain.manager.airconnect.dto.OperatorDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristGenerator;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.OPERATOR_PATH;


@CrossOrigin(origins = "*")
@RequestMapping(OPERATOR_PATH)
public interface OperatorAPI {

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE')")
    @GetMapping({"operatorNo"})
    public ChaineristResponse<Object> getByOperatorNo(@PathVariable("operatorNo") String operatorNo);

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ChaineristResponse<Object> save(@RequestBody OperatorDTO operatorDTO);

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @PutMapping
    public ChaineristResponse<Object> update(@RequestBody OperatorDTO operatorDTO);

    @PreAuthorize("hasAnyAuthority('ADMIN','AIRPORT','AIRLINE')")
    @GetMapping
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);


}
