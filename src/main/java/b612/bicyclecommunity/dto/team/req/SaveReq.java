package b612.bicyclecommunity.dto.team.req;

import b612.bicyclecommunity.domain.team.Kind;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveReq {
    private String name;
    private String comment;
    private String address;
    private LocalDateTime createdAt;
    private Kind kind;
}
