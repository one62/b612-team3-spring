package b612.bicyclecommunity.domain.refreshtoken;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RefreshToken {

    @Id
    @Column(name = "token_id")
    private String id;

    @Column(name = "token_value")
    private String token;

    public void changeTokenValue(String token) {
        this.token = token;
    }
}
