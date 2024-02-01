package b612.bicyclecommunity.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_loginKind")
    private LoginKind loginKind;

    @Column(name = "user_age")
    private Integer age;

    /**unique 해야 함*/
    @Column(name = "user_name")
    private String name;

    public User(String id, LoginKind loginKind){
        this.id = id;
        this.loginKind = loginKind;
    }

}
