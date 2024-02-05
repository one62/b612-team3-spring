package b612.bicyclecommunity.domain.user;

import b612.bicyclecommunity.domain.courseUser.CourseUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

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
    @Enumerated(EnumType.STRING)
    private LoginKind loginKind;

    @Column(name = "user_age")
    private Integer age;

    /**unique 해야 함*/
    @Column(name = "user_name")
    private String name;

    @Column(name = "user_address")
    private String address;

    @Column(name = "user_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(String id, LoginKind loginKind){
        this.id = id;
        this.loginKind = loginKind;
        this.name = UUID.randomUUID().toString();
    }

    public void edit(Integer age, String name, String address, Gender gender) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.gender = gender;
    }
}
