package icet.edu.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestUserLoginRequest {
     private String username;
     private String password;
}
