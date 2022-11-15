package AuthService.service.auth;

import AuthService.dto.auth.LoginRequest;
import AuthService.dto.auth.LoginResponse;
import AuthService.dto.auth.LogoutResponse;
import AuthService.dto.location.City;
import AuthService.dto.location.Country;
import AuthService.dto.person.PersonData;
import AuthService.security.PersonDetails;
import AuthService.security.service.JwtService;
import AuthService.util.security.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationProvider authenticationProvider;

    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        assertBlockCondition(personDetails);
        return buildLoginResponse(personDetails);
    }

    private LoginResponse buildLoginResponse(PersonDetails personDetails) {
        String token = jwtService.generateJwtToken(personDetails);
        return LoginResponse.builder()
                .personData(PersonData.builder()
                        .id(personDetails.getId())
                        .firstName(personDetails.getFirstName())
                        .lastName(personDetails.getLastName())
                        .regDate(personDetails.getRegDate())
                        .birthDate(personDetails.getBirthDate())
                        .email(personDetails.getEmail())
                        .phone(personDetails.getPhone())
                        .photo(personDetails.getPhoto())
                        .about(personDetails.getAbout())
                        .city(Optional.ofNullable(personDetails.getCity())
                                .map(city -> City.builder()
                                    .id(city.getId())
                                    .title(city.getTitle())
                                .build()).orElse(null))
                        .country(Optional.ofNullable(personDetails.getCountry())
                                .map(country -> Country.builder()
                                    .id(country.getId())
                                    .title(country.getTitle())
                                .build()).orElse(null))
                        .messagePermission(personDetails.getMessagePermission())
                        .lastOnlineTime(personDetails.getLastOnlineTime())
                        .isBlocked(personDetails.isBlocked())
                        .token(token)
                        .build())
                .build();
    }

    private void assertBlockCondition(PersonDetails personDetails) {
        if (personDetails.isBlocked()) {
            throw new RuntimeException("Error! User is blocked!");
        }
    }

    public LogoutResponse logout(HttpServletRequest request) {
        jwtService.blockToken(TokenUtil.parseToken(request));
        return new LogoutResponse();
    }
}
