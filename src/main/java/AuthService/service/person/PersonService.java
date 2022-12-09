package AuthService.service.person;

import AuthService.dto.person.PersonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("person-service/api/v1/account")
public interface PersonService {
    @PostMapping
    PersonDTO createPerson(@RequestBody PersonDTO personDTO);

    @GetMapping("/{email}")
    PersonDTO getPersonDTOByEmail(@PathVariable(name = "email") String email);

    @PutMapping
    void updateCustomer(@RequestBody PersonDTO personDTO);
}
