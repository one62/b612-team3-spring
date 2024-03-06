package b612.bicyclecommunity.dto.user.res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenRes {

    private String refreshToken;
    private String accessToken;
}
