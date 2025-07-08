package icet.edu.config;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityUtil {

    private  Keycloak keycloak;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.config.realm}")
    private String realm;

    @Value("${keycloak.config.client-id}")
    private String clientId;

    @Value("${keycloak.config.grant-type}")
    private String grantType;

    @Value("${keycloak.config.name}")
    private String username;

    @Value("${keycloak.config.password}")
    private String password;

    @Value("${keycloak.config.client-secret}")
    private String clientSecret;

    public Keycloak getKeycloakInstance() {
        if (keycloak == null) {
            keycloak = Keycloak.getInstance(
                    serverUrl,
                    realm,
                    username,
                    password,
                    clientId,
                    clientSecret
            );
        }
        return keycloak;
    }
}
