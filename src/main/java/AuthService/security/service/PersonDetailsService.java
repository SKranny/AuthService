package AuthService.security.service;

import AuthService.security.PersonDetails;
import AuthService.service.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(personService.getPersonDTOByEmail(email))
                .map(PersonDetails::build)
                .orElseThrow(() -> new UsernameNotFoundException("Error! Customer not found!"));
    }
}
