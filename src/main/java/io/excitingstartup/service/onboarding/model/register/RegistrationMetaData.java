package io.excitingstartup.service.onboarding.model.register;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Arun Vishnu
 */

@Data
public class RegistrationMetaData {
    @NotBlank( message = "firstName must be provided." )
    String firstName;
    @NotBlank ( message = "lastName must be provided." )
    String lastName;
    @NotBlank( message = "userName must be provided." )
    String userName;
    @NotBlank( message = "password must be provided." )
    String password;
    @NotBlank( message = "accountType must be provided." )
    String accountType;
    @NotBlank( message = "planName must be provided." )
    String planName;
}
