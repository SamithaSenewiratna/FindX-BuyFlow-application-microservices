package icet.edu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseUserAvater {
    private String hash;
    private String  dirctory;
    private String fileName;
    private String resourceUrl;
}
