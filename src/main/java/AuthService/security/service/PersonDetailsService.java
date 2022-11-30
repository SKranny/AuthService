package AuthService.security.service;

import AuthService.dto.person.PersonDTO;
import AuthService.security.PersonDetails;
import AuthService.service.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return PersonDetails.build(personService.getPersonDTOByEmail(email));
    }
}
