package b612.bicyclecommunity.dto.team.res;

import b612.bicyclecommunity.domain.team.Kind;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SearchRes {

    String name;
    String comment;
    String address;
    LocalDateTime createdAt;
    Kind kind;
}
