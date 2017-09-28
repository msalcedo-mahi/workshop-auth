package com.workshop.service;

import com.workshop.rest.dto.PlayerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PlayerService {

    public PlayerDTO getLastUpdatesInfo()  {
        return PlayerDTO.builder().
                name("John").
                lastName("Smith").
                build();
    }
}
