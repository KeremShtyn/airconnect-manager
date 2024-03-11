package com.chainerist.blockchain.manager;

import com.chainerist.blockchain.manager.authentication.domain.User;
import com.chainerist.blockchain.manager.authentication.entity.UserRole;
import com.chainerist.blockchain.manager.authentication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Profile("development")
@Component
@Slf4j
public class InitialData {

    public static final String USERNAME = "system";
    public static final String PASSWORD = "syst3m";

    private UserService userService;

    public InitialData(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initialValueOFSystem() {
        log.debug("Starting initial value of system");
        User user = null;
        try {
            user = userService.findByUsername(USERNAME);
        } catch (Exception e) {
            log.warn("System user could not found in system by username {}", USERNAME);
        }
        if (Objects.isNull(user)) {
            createUser(new User());
        }
        log.debug("Completed initial value of system");
    }

    private void createUser(User user) {
        log.debug("System user creating by username : {}", USERNAME);
        user.setUsername(USERNAME);
        user.setFirstName(USERNAME);
        user.setLastName("");
        user.setRole(UserRole.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(PASSWORD));
        userService.saveOrUpdate(user);
        log.debug("System user created by userId : {}", user.getId());


    }


}
