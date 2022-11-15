package AuthService.security.service;

import AuthService.constants.MessagePermission;
import AuthService.dto.location.City;
import AuthService.dto.location.Country;
import AuthService.dto.person.Person;
import AuthService.security.PersonDetails;
import AuthService.security.Role;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Map<String, Person> persons = new HashMap<>() {{
        this.put("l;sdfjks;d", Person.builder()
                        .id(1L)
                        .firstName("ksjdflksdf")
                        .lastName("dkfjlksf")
                        .regDate(System.currentTimeMillis() - 100000)
                        .birthDate(System.currentTimeMillis() - 1000)
                        .email("dl;fsdk;f")
                        .phone("dsfjdskfj")
                        .photo("/")
                        .about("")
                        .city(City.builder()
                                .id(1L)
                                .title("Котельники")
                                .build())
                        .country(Country.builder()
                                .id(1L)
                                .title("Россия")
                                .build())
                        .messagePermission(MessagePermission.All)
                        .isBlocked(false)
                        .roles(List.of(Role.ROLE_ADMIN, Role.ROLE_USER))
                        .password(passwordEncoder.encode("dsfkjksfdjl"))
                        .lastOnlineTime(System.currentTimeMillis())
                .build());
    }};

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return PersonDetails.build(getPersonByEmail(email));
    }

    private Person getPersonByEmail(String email) {
        return Optional.ofNullable(persons.get(email))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found.", email)));
    }
}
