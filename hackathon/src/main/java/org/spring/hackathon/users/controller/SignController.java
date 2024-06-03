package org.spring.hackathon.users.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.users.dto.MemberDto;
import org.spring.hackathon.users.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SignController {

  private final MemberService memberService;

  @PostMapping("/signup")
  public ResponseEntity<String> signUp(@RequestBody MemberDto dto){

    log.info("==================================회원가입 실행==================================");
    log.info(dto);

    memberService.signup(dto);

    return ResponseEntity.ok().body("회원가입 완료");

  }

  @PostMapping("/signin")
  public ResponseEntity<String> signIn(@RequestBody MemberDto dto){

    log.info("==================================로그인 실행==================================");

    String token = memberService.signin(dto.getMemberId(), dto.getMemberPassword());
    return ResponseEntity.ok().body(token);

  }

}
