package b612.bicyclecommunity.repository;

import b612.bicyclecommunity.domain.team.Team;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.domain.userTeam.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserTeamRepository extends JpaRepository<UserTeam, Integer> {

    @Query("select count(ut) from UserTeam ut where ut.team.name=:name")
    Integer countByTeamName(String name);

    @Query("select ut from UserTeam ut where ut.team = :team and ut.user = :user")
    public abstract Boolean teamHasUser(@Param("team") Team team, @Param("user") User user);

}
