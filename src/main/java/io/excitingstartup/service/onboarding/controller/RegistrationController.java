package io.excitingstartup.service.onboarding.controller;

import io.excitingstartup.service.onboarding.model.register.RegistrationMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Arun Vishnu
 */

@RestController
@Slf4j
public class RegistrationController {

    @PostMapping("/register")
    public ResponseEntity<RegistrationMetaData> register(@Valid @RequestBody RegistrationMetaData registrationMetaData){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
