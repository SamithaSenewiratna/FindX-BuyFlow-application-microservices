package security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorizeExchangeSpec ->
                        authorizeExchangeSpec.pathMatchers("/").permitAll()
                                .anyExchange()
                                .authenticated())
                                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
                        return serverHttpSecurity.build();
    }
}
