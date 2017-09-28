package com.workshop.service;

import com.workshop.rest.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService {

    public UserDTO getLastUpdatesInfo()  {
        UserDTO userDTO = UserDTO.builder().
                firstAtt("First Att").
                secondAtt("Second Att").
                build();
        log.debug(userDTO.getFirstAtt());
        log.debug(userDTO.getSecondAtt());
        return userDTO;
    }
}
