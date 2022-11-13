package AuthService.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
             .info(
                  new Info()
                       .description("Описание всех методов для микросервиса auth-service")
                       .title("Api для auth-service")
                       .version("1.0.0")
             );
    }
}
