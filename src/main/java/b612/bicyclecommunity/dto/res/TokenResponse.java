package b612.bicyclecommunity.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {

    private String refreshToken;
    private String accessToken;
}
