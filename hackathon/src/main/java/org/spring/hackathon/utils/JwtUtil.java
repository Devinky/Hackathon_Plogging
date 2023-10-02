package org.spring.hackathon.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

  public static String createToken(String userName, String secretKey, Long expiredMs) {

    Claims claims = Jwts.claims(); //일종의 MAP(정보를 넣는다), 유저네임 저장 가능
    claims.put("memberId", userName); //토큰을 열면 userName이 들어있다

    return Jwts.builder()
        .setClaims(claims) //Claim 지정
        .setIssuedAt(new Date(System.currentTimeMillis())) //토큰이 언제 이슈됐는지
        .setExpiration(new Date(System.currentTimeMillis() + expiredMs)) //토큰을 언제까지 유지할 것인지
        .signWith(SignatureAlgorithm.HS256, secretKey) //어떤 형태로 토큰을 발급하는지
        .compact(); //이 알고리즘으로 사인을 함을 선언

  }

}
