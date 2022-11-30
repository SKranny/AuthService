package AuthService.security;

import AuthService.constants.RoleType;
import AuthService.dto.person.PersonDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class PersonDetails implements UserDetails {
    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    @Builder.Default
    private boolean blocked = true;

    @Builder.Default
    private boolean accountNonExpired = true;

    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private boolean accountNonLocked = true;

    private List<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return String.format("%s %s", lastName, firstName);
    }

    public static PersonDetails build(PersonDTO personDTO) {
        return PersonDetails.builder()
                .id(personDTO.getId())
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .email(personDTO.getEmail())
                .blocked(personDTO.getIsBlocked())
                .password(personDTO.getPassword())
                .authorities(personDTO.getRoles().stream()
                        .map(RoleType::name)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()))
                .build();
    }
}
