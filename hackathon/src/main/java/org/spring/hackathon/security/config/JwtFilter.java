package org.spring.hackathon.security.config;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.member.service.MemberService;
import org.spring.hackathon.security.utils.JwtProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final static String HEADER_AUTHORIZATION = "Authorization";
  private final static String TOKEN_PREFIX = "Bearer ";
  private final MemberService memberService;
  private final JwtProperties jwtProperties;
  private final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //토큰이 있는지 매번 확인
    //인증할 때 통과하는 문 역할

    //요청 헤더의 Authorization 키의 값 조회
    String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
    //가져온 값에서 접두사 제거(Bearer)
    String token = getAccessToken(authorizationHeader);

    if(jwtProvider.validToken(token)) {
//      Authentication authentication = jwtProvider.
    }

    //권한 부여
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("MEMBER")));

    //Details 넣기
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request, response);

  }

  private String getAccessToken(String authorizationHeader) {

    if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
      return authorizationHeader.substring(TOKEN_PREFIX.length());
    }
    return null;
  }

}
