package b612.bicyclecommunity.dto.team.req;

import b612.bicyclecommunity.domain.userTeam.Level;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class JoinReq {
    String name;
    LocalDateTime joinedAt;
}
