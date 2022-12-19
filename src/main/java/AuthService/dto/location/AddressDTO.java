package AuthService.dto.location;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Адрес")
public class AddressDTO {
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Город")
    private String city;
    @Schema(description = "Страна")
    private String country;
}
