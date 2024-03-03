package b612.bicyclecommunity.domain.userTeam;

import b612.bicyclecommunity.domain.team.Team;
import b612.bicyclecommunity.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userteam_id")
    private Integer id;

    @Column(name = "userteam_joined_at")
    private LocalDateTime joinedAt;

    @Column(name = "userteam_user_level")
    private Level level;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public UserTeam(LocalDateTime joinedAt, Level level, User user, Team team){
        this.joinedAt = joinedAt;
        this.level = level;
        this.user = user;
        this.team = team;
    }
}
