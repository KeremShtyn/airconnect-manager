package com.chainerist.blockchain.manager.util;

import com.chainerist.blockchain.manager.authentication.domain.UserInfo;
import com.chainerist.blockchain.manager.authentication.entity.UserEntity;
import com.chainerist.blockchain.manager.authentication.entity.WorkingUnitEntity;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class AuthenticationUtil {

    public static UserInfo getUserInformation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication) && Objects.nonNull(authentication.getPrincipal())) {
            UserEntity user = (UserEntity) authentication.getPrincipal();
            Optional<WorkingUnitEntity> airlineUser = user.getWorkingUnits().stream().filter(f -> f.getWorkingType().equals("AIRLINE")).findFirst();

            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user.getId());
            userInfo.setAirlineId(airlineUser.isPresent() ? airlineUser.get().getWorkingId() : null);

            return userInfo;
        }
        return null;
    }
    public static UserInfo getPassengerInformation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication) && Objects.nonNull(authentication.getPrincipal())) {
            UserEntity user = (UserEntity) authentication.getPrincipal();
            Optional<WorkingUnitEntity> passengerUser = user.getWorkingUnits().stream().filter(f -> f.getWorkingType().equals("USER")).findFirst();

            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user.getId());
            userInfo.setPassengerId(passengerUser.isPresent() ? passengerUser.get().getWorkingId() : null);

            return userInfo;
        }
        return null;
    }

}
