package b612.bicyclecommunity.global.jwt;

import b612.bicyclecommunity.domain.refreshtoken.RefreshToken;
import b612.bicyclecommunity.repository.RefreshTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String accessToken = extractToken(request, "accessToken");
        String refreshToken = extractToken(request, "refreshToken");

        if(jwtProvider.validateToken(accessToken)){
            Authentication authentication = jwtProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String id = jwtProvider.getID(accessToken);
            String newAccessToken = jwtProvider.createAccessToken(id);
            response.setHeader("accessToken", "Bearer " + newAccessToken);

            filterChain.doFilter(request,response);
        } else if (jwtProvider.validateToken(refreshToken)){

            RefreshToken refreshTokenInRepo = refreshTokenRepository.findByToken(refreshToken).orElseThrow(() -> new RuntimeException("재로그인 필요."));

            String newRefreshToken = jwtProvider.createRefreshToken();
            String newAccessToken = jwtProvider.createAccessToken(refreshTokenInRepo.getId());


            refreshTokenInRepo.changeTokenValue(newRefreshToken);
            refreshTokenRepository.save(refreshTokenInRepo);

            Authentication authentication = jwtProvider.getAuthentication(newAccessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            response.setHeader("accessToken", "Bearer " + newAccessToken);
            response.setHeader("refreshToken", "Bearer " + newRefreshToken);

            filterChain.doFilter(request, response);
        } else{
            filterChain.doFilter(request,response);
        }
    }

    public String extractToken(HttpServletRequest httpServletRequest, String tokenName){
        return httpServletRequest.getHeader(tokenName);
    }
}
