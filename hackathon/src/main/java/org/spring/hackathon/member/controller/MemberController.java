package org.spring.hackathon.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/member")
@CrossOrigin(exposedHeaders = "Authorization")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  //가입, 로그인 관련은 signController에서 처리

  @GetMapping("/mypage/{memberId}")
  public ResponseEntity<MemberDto> memberMyPage(@PathVariable Long memberId){

    MemberDto memberDto = memberService.memberMyPageView(memberId);

    return new ResponseEntity<>(memberDto, HttpStatus.valueOf(200));

  }

}
