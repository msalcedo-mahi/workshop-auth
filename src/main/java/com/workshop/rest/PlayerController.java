package com.workshop.rest;

import com.workshop.rest.dto.PlayerDTO;
import com.workshop.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@ControllerAdvice
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;


    @RequestMapping(method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public PlayerDTO simpleRequest(){
        return playerService.getLastUpdatesInfo();
    }

}
