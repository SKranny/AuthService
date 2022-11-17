package AuthService.security.service;

import AuthService.dto.person.Person;
import AuthService.security.PersonDetails;
import AuthService.security.Role;
import lombok.RequiredArgsConstructor;
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

//    2 строчки ниже под угрозой уничтожения
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Map<String, Person> persons = new HashMap<>() {{
        this.put("test@gmail.com", Person.builder()
                        .id(1L)
                        .firstName("Пупок")
                        .lastName("Пупкин")
                        .email("test@gmail.com")
                        .isBlocked(false)
                        .roles(List.of(Role.ROLE_ADMIN, Role.ROLE_USER))
                        .password(passwordEncoder.encode("pass123"))
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
