package AuthService.security;

import AuthService.constants.MessagePermission;
import AuthService.dto.location.City;
import AuthService.dto.location.Country;
import AuthService.dto.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Data
public class PersonDetails implements UserDetails {
    private Long id;

    private String firstName;

    private String lastName;

    private Long regDate;

    private Long birthDate;

    private String email;

    private String phone;

    private String photo;

    private String about;

    private City city;

    private Country country;

    private MessagePermission messagePermission;

    private Long lastOnlineTime;

    private Boolean isBlocked;

    private List<Role> roles;

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

    public static PersonDetails build(Person person) {
        return PersonDetails.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .regDate(person.getRegDate())
                .birthDate(person.getBirthDate())
                .email(person.getEmail())
                .phone(person.getPhone())
                .photo(person.getPhoto())
                .about(person.getAbout())
                .city(Optional.ofNullable(person.getCity())
                        .map(c -> City.builder()
                                .id(c.getId())
                                .title(c.getTitle())
                                .build())
                        .orElse(null))
                .country(Optional.ofNullable(person.getCountry())
                        .map(c -> Country.builder()
                                .id(c.getId())
                                .title(c.getTitle())
                                .build())
                        .orElse(null))
                .messagePermission(person.getMessagePermission())
                .lastOnlineTime(person.getLastOnlineTime())
                .blocked(person.getIsBlocked())
                .password(person.getPassword())
                .authorities(person.getRoles().stream()
                        .map(Role::name)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()))
                .build();
    }
}
