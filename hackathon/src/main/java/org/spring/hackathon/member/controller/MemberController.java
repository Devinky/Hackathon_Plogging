package org.spring.hackathon.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  //회원 정보 조회, 수정, 탈퇴 처리 Controller
  //가입, 로그인 관련은 signController에서 처리
  private final MemberService memberService;

  @GetMapping("/mypage/{memberId}")
  public String memberMypage(Model model){

    return "";
  }

}
