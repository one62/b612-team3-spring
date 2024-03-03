package b612.bicyclecommunity.dto.team.res;

import b612.bicyclecommunity.domain.team.Kind;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class InfoRes {
    private String name;
    private String comment;
    private String address;
    private LocalDateTime createdAt;
    private Kind kind;
    private Integer count;
}
