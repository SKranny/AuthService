package AuthService.dto.person;

import AuthService.constants.MessagePermission;
import AuthService.dto.location.City;
import AuthService.dto.location.Country;
import AuthService.security.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("reg_date")
    private Long regDate;

    @JsonProperty("birth_date")
    private Long birthDate;

    private String email;

    private String phone;

    private String photo;

    private String about;

    private City city;

    private Country country;

    @JsonProperty("messages_permission")
    private MessagePermission messagePermission;

    @JsonProperty("last_online_time")
    private Long lastOnlineTime;

    @JsonProperty("is_blocked")
    private Boolean isBlocked;

    @JsonProperty("type")
    private List<Role> roles;

    private String password;
}
