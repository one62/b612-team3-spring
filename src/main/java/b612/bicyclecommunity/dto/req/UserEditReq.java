package b612.bicyclecommunity.dto.req;

import b612.bicyclecommunity.domain.user.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserEditReq {
    @NotNull(message = "나이가 필요합니다.")
    private Integer age;

    @NotBlank(message = "이름이 필요합니다.")
    private String name;

    @NotBlank(message = "주소가 필요합니다.")
    private String address;

    @Valid()
    private Gender gender;
}
