package b612.bicyclecommunity.service;

import b612.bicyclecommunity.domain.refreshtoken.RefreshToken;
import b612.bicyclecommunity.domain.team.Team;
import b612.bicyclecommunity.domain.user.Gender;
import b612.bicyclecommunity.domain.user.LoginKind;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.domain.userTeam.UserTeam;
import b612.bicyclecommunity.dto.team.res.SearchRes;
import b612.bicyclecommunity.dto.user.res.TokenRes;
import b612.bicyclecommunity.dto.user.res.UserInfoRes;
import b612.bicyclecommunity.global.jwt.JwtProvider;
import b612.bicyclecommunity.repository.RefreshTokenRepository;
import b612.bicyclecommunity.repository.TeamRepository;
import b612.bicyclecommunity.repository.UserRepository;
import b612.bicyclecommunity.repository.UserTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TeamRepository teamRepository;
    private final UserTeamRepository userTeamRepository;

    @Transactional
    public TokenRes mobileKakao(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = new User(id, LoginKind.KaKao);
            userRepository.save(user);
        }

        String accessToken = jwtProvider.createAccessToken(id);
        String refreshToken = jwtProvider.createRefreshToken();

        refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken));

        return new TokenRes(refreshToken, accessToken);
    }

    @Transactional
    public UserInfoRes info(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));

        return new UserInfoRes(user.getAge(), user.getName(), user.getAddress(), user.getGender());
    }

    @Transactional
    public UserInfoRes edit(String userId, Integer age, String name, String address, Gender gender) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));

        user.edit(age, name, address, gender);

        return new UserInfoRes(user.getAge(), user.getName(), user.getAddress(), user.getGender());
    }

    @Transactional
    public List<SearchRes> teams(String userId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));
        Page<SearchRes> teams = userTeamRepository.findByUser(user, pageable);

        return teams.getContent();
    }
}
