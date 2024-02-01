package b612.bicyclecommunity.service;

import b612.bicyclecommunity.domain.user.LoginKind;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.dto.res.TokenResponse;
import b612.bicyclecommunity.global.jwt.JwtProvider;
import b612.bicyclecommunity.repository.RefreshTokenRepository;
import b612.bicyclecommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponse mobileKakao(String id) {
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

        return new TokenResponse(refreshToken, accessToken);
    }
}
