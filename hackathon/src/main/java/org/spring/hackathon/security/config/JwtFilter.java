package org.spring.hackathon.security.config;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.security.utils.JwtProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final static String HEADER_AUTHORIZATION = "Authorization";
  private final static String TOKEN_PREFIX = "Bearer ";
  private final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //토큰이 있는지 매번 확인
    //인증할 때 통과하는 문 역할

    //요청 메시지 헤더의 Authorization 키의 값 조회
    String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
    System.out.println(authorizationHeader);

    //가져온 값에서 접두사 제거(Bearer) -> getAccessToken 호출
    String token = getAccessToken(authorizationHeader);
    System.out.println(token);

    //가져온 토큰이 유효한지 검증, 인증 정보를 설정
    if(jwtProvider.validToken(token)) {
      Authentication authentication = jwtProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);

  }

  private String getAccessToken(String authorizationHeader) {
    //조건문 : authorizationHeader가 null이 아니고 Bearer 로 시작하는 문자열일 때 실행
    if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
      //Bearer 자르기
      return authorizationHeader.substring(TOKEN_PREFIX.length());
    }
    System.out.println(authorizationHeader);
    return null;
  }

}
