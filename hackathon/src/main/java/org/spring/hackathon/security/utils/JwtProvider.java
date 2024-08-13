package org.spring.hackathon.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class JwtProvider {

  private final JwtProperties jwtProperties;

  public String generateToken(Duration expiredTime, String memberId){
    Date now = new Date();
    return createToken(new Date(now.getTime() + expiredTime.toMillis()), memberId);
  }

  public String createToken(Date expiredTime, String memberId) {

    Date now = new Date();

    return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(jwtProperties.getIssuer()) //발급자
            .setIssuedAt(now) //토큰 발급 시간
            .setExpiration(expiredTime) //토큰 만료 시간
            .setSubject(memberId) //" token" 문자열 제거 *수정
            .claim("memberID ", memberId) // 콜론 제거 *수정
            .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) //해시값 종류와 시크릿키 부여
            .compact(); //상기 설정으로 사인함을 선언

  }

  public boolean validToken(String token){

    try{
      Jwts.parser()
              .setSigningKey(jwtProperties.getSecretKey())
              .parseClaimsJws(token);
      return true;
    } catch (Exception e){
      //예외 발생 시 로그 출력
      e.printStackTrace();
      return false;
    }

  }

  public Authentication getAuthentication(String token) {

    Claims claims = getClaims(token);
    Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER"));

    return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User
            (claims.getSubject(), "", authorities), token, authorities);

  }

  public String getUserId(String token) {
    Claims claims = getClaims(token);
    return claims.get("memberID ", String.class);
  }

  private Claims getClaims(String token) {

    return Jwts.parser() //클레임 조회
            .setSigningKey(jwtProperties.getSecretKey())
            .parseClaimsJws(token)
            .getBody();

  }

}
