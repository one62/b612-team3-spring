package b612.bicyclecommunity.domain.team;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer id;

    @Column(name = "team_name", unique = true)
    private String name;

    @Column(name = "team_comment")
    private String comment;

    @Column(name = "team_location")
    private String address;

    @Column(name = "team_created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "team_kind")
    private Kind kind;


    public Team(String name, String comment, String address, LocalDateTime createdAt, Kind kind){
        this.name = name;
        this.comment = comment;
        this.address = address;
        this.createdAt = createdAt;
        this.kind = kind;
    }
}
