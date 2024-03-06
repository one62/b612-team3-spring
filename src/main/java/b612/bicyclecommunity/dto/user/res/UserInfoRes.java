package b612.bicyclecommunity.dto.user.res;

import b612.bicyclecommunity.domain.user.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoRes {

    private Integer age;
    private String name;
    private String address;
    private Gender gender;
}
