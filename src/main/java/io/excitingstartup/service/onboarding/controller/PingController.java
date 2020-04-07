package io.excitingstartup.service.onboarding.controller;

import io.excitingstartup.service.onboarding.service.RegistrationService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author Arun Vishnu
 */

@RestController
@Slf4j
@RequestMapping("")
@Data
public class PingController {


    private final RegistrationService registrationService;

    @Inject
    public PingController( final RegistrationService registrationService){
        this.registrationService = registrationService;
    }


    @GetMapping("/ping")
    public String ping(){
        log.info("Received ping request..");
        log.info("Responding with PONG..");
        Boolean dbCreated = registrationService.createDB();
        log.info( "DB Created?" + dbCreated.toString() );
        return ("PONG");
    }
}
