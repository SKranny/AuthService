package AuthService.service.auth;

import AuthService.dto.auth.LoginRequest;
import AuthService.dto.auth.RegisterRequest;
import AuthService.dto.person.PersonDTO;
import AuthService.security.PersonDetails;
import AuthService.constants.RoleType;
import AuthService.security.service.JwtService;
import AuthService.service.person.PersonService;
import AuthService.service.mail.MailService;
import AuthService.service.recovery.RecoveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationProvider authenticationProvider;

    private final PersonService personService;

    private final MailService mailService;

    private final RecoveryService recoveryService;

    private final JwtService jwtService;

    public String login(LoginRequest request) {
        Authentication authentication = authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        assertBlockCondition(personDetails);
        return jwtService.generateJwtToken(personDetails);
    }

    private void assertBlockCondition(PersonDetails personDetails) {
        if (personDetails.isBlocked()) {
            throw new RuntimeException("Error! User is blocked!");
        }
    }

    private void assertPasswordEqual(String password, String confirmPassword) {
        if (!Optional.ofNullable(password).equals(Optional.ofNullable(confirmPassword))) {
            throw new RuntimeException("Error! Passwords is not equals");
        }
    }

    private void assertCodeEqual(String code) {

    }

    private PersonDTO buildCustomer(RegisterRequest request) {
        return PersonDTO.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roles(Collections.singleton(RoleType.ROLE_USER))
                .build();
    }

    public String createCustomer(RegisterRequest request) {
        assertPasswordEqual(request.getPassword(), request.getConfirmPassword());
        assertCodeEqual(request.getCode());

        personService.createPerson(buildCustomer(request));

        return login(LoginRequest.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                .build());
    }

    public void resetPass(String uuid, String password) {
        PersonDTO personDTO = personService.getPersonDTOByEmail(recoveryService.getAndDeleteRecoveryPassRequest(uuid).getEmail());
        personDTO.setPassword(passwordEncoder.encode(password));
        personService.updateCustomer(personDTO);
    }

    public void createRecoveryPassRequest(String email) {
        if (personService.getPersonDTOByEmail(email) != null) {
            mailService.sendRecoveryMail(new String[]{email},
                    recoveryService.createRecoveryPassRequest(email).getUuid());
        }
    }

}
