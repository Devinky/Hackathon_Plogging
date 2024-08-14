package org.spring.hackathon.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

@RestController
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  //회원 정보 조회, 수정, 탈퇴 처리 Controller
  //가입, 로그인 관련은 signController에서 처리
  private final MemberService memberService;

  @GetMapping("/mypage/{memberId}")
  public ResponseEntity<MemberDto> memberMyPage(@PathVariable Long memberId){

    MemberDto memberDto = memberService.memberMyPageView(memberId);
    return new ResponseEntity<>(memberDto, HttpStatus.valueOf(200));

  }

  @PatchMapping("/update/{memberId}")
  public ResponseEntity<String> memberInfoUpdate(@PathVariable Long memberId, @RequestBody MemberDto dto){

    MemberDto memberDto = memberService.memberInfoUpdate(memberId, dto);
    return ResponseEntity.ok().body("수정 완료");

  }

}
