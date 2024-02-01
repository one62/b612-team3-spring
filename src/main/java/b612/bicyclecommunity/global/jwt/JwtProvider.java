package b612.bicyclecommunity.global.jwt;

import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.global.security.UserDetailsImpl;
import b612.bicyclecommunity.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final UserRepository userRepository;
    Key key;

    @PostConstruct
    public void init(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    /**
     * AccessJWT 생성
     * 유효 시간은 10분
     * @param(Integer) userID
     * @return
     */
    public String createAccessToken(String userID){
        Date now = new Date();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes((10)).toMillis()))
                .setSubject(userID)
                .signWith(key)
                .compact();
    }

    /**
     * RefreshToken 생성
     * 유효 시간은 7일
     * @return
     */
    public String createRefreshToken(){
        Date now = new Date();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(7).toMillis()))
                .signWith(key)
                .compact();
    }

    /**
     * 토큰 유효성 확인
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 토큰을 받아서 토큰의 subject 를 String 으로 추출
     * @param(String) token
     * @return
     */
    public String getID(String token){
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    /**
     * 토큰을 받아서 인증 객체를 생성
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token){
        String id = this.getID(token);

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("유저가 존재하지 않음"));

        UserDetailsImpl userDetails = new UserDetailsImpl(user.getId());

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
