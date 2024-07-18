package org.spring.hackathon.security.config;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.member.service.MemberService;
import org.spring.hackathon.security.utils.JwtProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtProperties jwtProperties;
  private final MemberService memberService;

  @Bean
  public SecurityFilterChain webConfig(HttpSecurity http) throws Exception {
    return http
        .httpBasic().disable()
        .csrf().disable()
        .cors().and()
        .authorizeHttpRequests()
        .antMatchers("/member/**", "/plogging/**").hasRole("MEMBER")
        .anyRequest().permitAll()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //JWT 사용하는 경우
        .and()
//        .addFilterBefore(new JwtFilter(memberService, jwtProperties.getSecretKey()), UsernamePasswordAuthenticationFilter.class)
        .build();
  }

}
