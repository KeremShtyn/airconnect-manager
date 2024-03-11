package com.chainerist.blockchain.manager.authentication.api;

import com.chainerist.blockchain.manager.airconnect.dto.TicketDTO;
import com.chainerist.blockchain.manager.authentication.dto.TokenRequestDTO;
import static com.chainerist.blockchain.manager.util.api.ChaineristAPIEnpoint.*;

import com.chainerist.blockchain.manager.authentication.dto.UserDTO;
import com.chainerist.blockchain.manager.util.response.ChaineristResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(AUTH_PATH)
public interface AuthAPI {

    @PostMapping(CREATE_TOKEN)
    public ChaineristResponse<Object> token(@RequestBody TokenRequestDTO request);

    @PostMapping(CREATE_USER)
    public ChaineristResponse<Object> saveUser(@RequestBody UserDTO userDTO);

    @PutMapping(CREATE_USER)
    public ChaineristResponse<Object> update(@RequestBody UserDTO userDTO);

    @GetMapping("/getByUsername/{username}")
    public ChaineristResponse<Object> getByUsername(@PathVariable("username") String username);

    @GetMapping("/users")
    public ChaineristResponse<Object> getPage(@RequestHeader Map<String, Object> header, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "modifyDate:DESC", required = false) String[] sortBy);



}
