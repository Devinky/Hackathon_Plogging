package org.spring.hackathon.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.spring.hackathon.security.config.JwtProperties;
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

  public String generateToken(String memberId, Duration expiredTime){
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
            .setSubject(memberId + " token")
            .claim("ID : ", memberId)
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
      return false;
    }

  }

  public Authentication gerAuthentication(String token) {

    Claims claims = getClaims(token);
    Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("MEMBER"));

    return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User
            (claims.getSubject(), "", authorities), token, authorities);

  }

  public Long getUserId(String token) {
    Claims claims = getClaims(token);
    return claims.get("memberId : ", Long.class);
  }

  private Claims getClaims(String token) {

    return Jwts.parser() //클레임 조회
            .setSigningKey(jwtProperties.getSecretKey())
            .parseClaimsJwt(token)
            .getBody();

  }

}
