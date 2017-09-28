package com.workshop.rest;

import com.workshop.rest.dto.UserDTO;
import com.workshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@ControllerAdvice
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO simpleRequest(){
        return userService.getLastUpdatesInfo();
    }

}
