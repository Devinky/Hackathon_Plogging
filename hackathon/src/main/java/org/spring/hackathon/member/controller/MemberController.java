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

  //회원 정보 조회, 수정, 탈퇴를 처리하는 Controller
  //가입, 로그인 관련은 signController에서 처리
  private final MemberService memberService;

  //회원 정보 조회
  @GetMapping("/mypage/{memberNo}")
  public ResponseEntity<MemberDto> memberMyPage(@PathVariable Long memberNo, @RequestHeader("Authorization") String token) {

    MemberDto memberDto = memberService.memberMyPageView(memberNo, token);
    return new ResponseEntity<>(memberDto, HttpStatus.valueOf(200));

  }

  //회원 정보 수정
  @PatchMapping("/update/{memberNo}")
  public ResponseEntity<String> memberInfoUpdate(@PathVariable Long memberNo, @RequestBody MemberDto dto,
                                                 @RequestHeader("Authorization") String token) {

    MemberDto memberDto = memberService.memberInfoUpdate(memberNo, dto, token);
    return ResponseEntity.ok().body("수정 완료");

  }

  //회원 탈퇴
  @DeleteMapping("/delete/{memberNo}")
  public ResponseEntity<String> memberDelete(@PathVariable Long memberNo, @RequestHeader("Authorization") String token) {

    memberService.memberDelete(memberNo, token);
    return ResponseEntity.ok().body("탈퇴 완료");

  }

}
