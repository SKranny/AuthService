package AuthService.service.auth;

import AuthService.dto.auth.LoginRequest;
import AuthService.security.PersonDetails;
import AuthService.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationProvider authenticationProvider;

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

}
