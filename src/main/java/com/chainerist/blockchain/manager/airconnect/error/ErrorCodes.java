package com.chainerist.blockchain.manager.airconnect.error;

import com.chainerist.blockchain.manager.util.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ErrorCodes implements ErrorCode {

    SYSTEM_FAILURE(-1, "ErrorCodes.SYSTEM_FAILURE", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND(100, "ErrorCodes.ACCESS_DENIED", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(100, "ErrorCodes.USER_DENIED", HttpStatus.BAD_REQUEST),

    ACCESS_DENIED(101, "ErrorCodes.ACCESS_DENIED", HttpStatus.FORBIDDEN),

    STATION_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.TICKET_IDENTIFIER_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),

    PASSENGER_DATA_CAN_NOT_BE_EMPTY(101, "ErrorCodes.PASSENGER_DATA_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    PASSENGER_FIRSTNAME_OR_LASTNAME_CAN_NOT_BE_EMPTY(101, "ErrorCodes.PASSENGER_FIRSTNAME_OR_LASTNAME_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    PASSENGER_IDENTIFIER_CAN_NOT_BE_EMPTY(101, "ErrorCodes.PASSENGER_IDENTIFIER_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    PASSENGER_ALREADY_EXISTS(101, "ErrorCodes.PASSENGER_ALREADY_EXISTSs", HttpStatus.BAD_REQUEST),

    STATION_ALREADY_EXISTS(101, "ErrorCodes.STATION_ALREADY_EXISTS", HttpStatus.BAD_REQUEST),
    AIRCRAFT_ALREADY_EXISTS(101, "ErrorCodes.AIRCRAFT_ALREADY_EXISTS", HttpStatus.BAD_REQUEST),
    AIRPORT_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRPORT_DATA_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRPORT_IATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRPORT_IATA_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRPORT_ICAO_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRPORT_ICAO_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRPORT_COUNTRY_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRPORT_COUNTRY_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRPORT_NAME_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRPORT_NAME_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRPORT_CITY_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRPORT_CITY_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRLINE_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRLINE_DATA_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRLINE_IATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRLINE_IATA_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRLINE_ICAO_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRLINE_ICAO_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRLINE_COUNTRY_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRLINE_COUNTRY_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRLINE_NAME_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRLINE_NAME_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRLINE_CALLSIGN_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRLINE_CALLSIGN_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    OPERATOR_NAME_CAN_NOT_BE_EMPTY(101,"ErrorCodes.OPERATOR_NAME_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    OPERATOR_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRPORT_DATA_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    OPERATOR_NO_CAN_NOT_BE_EMPTY(101,"ErrorCodes.OPERATOR_NO_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    FLIGHT_NO_CAN_NOT_BE_EMPTY(101,"ErrorCodes.FLIGHT_NO_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),
    FLIGHT_IDENTIFIER_CAN_NOT_BE_EMPTY(101,"ErrorCodes. FLIGHT_IDENTIFIER_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRCRAFT_LEG_NO_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRCRAFT_LEG_NO_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRCRAFT_TYPE_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRCRAFT_TYPE_CAN_NOT_BE_EMPTY",HttpStatus.BAD_REQUEST),

    AIRCRAFT_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.AIRCRAFT_DATA_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),

    GATE_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.GATE_DATA_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),

    GATE_CODE_CAN_NOT_BE_EMPTY(101,"ErrorCodes.GATE_CODE_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),

    TICKET_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.TICKET_DATA_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),

    TICKET_SEAT_CAN_NOT_BE_EMPTY(101,"ErrorCodes.TICKET_SEAT_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),

    TICKET_IDENTIFIER_CAN_NOT_BE_EMPTY(101,"ErrorCodes.TICKET_IDENTIFIER_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),

    STATION_NO_CAN_NOT_BE_EMPTY(101, "ErrorCodes.STATION_NO_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),


    ;


    private ErrorCodes(Integer code, String langKey, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.langKey = langKey;
    }

    @Getter
    private Integer code;

    @Getter
    private String langKey;

    @Getter
    private HttpStatus httpStatus;

    /**
     * @param code
     * @return
     */
    public ErrorCodes findByCode(Integer code) {
        return Arrays.asList(ErrorCodes.values()).stream().filter(f -> f.getCode().equals(code)).findFirst().orElse(ErrorCodes.SYSTEM_FAILURE);
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String langKey() {
        return this.langKey;
    }
    }
