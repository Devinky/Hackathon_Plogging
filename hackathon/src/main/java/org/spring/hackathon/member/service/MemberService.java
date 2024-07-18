package org.spring.hackathon.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.member.constructor.MemberConstructor;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;


  //마이페이지 회원정보 조회
  public MemberDto memberMyPageView(Long memberId) {

    Optional<MemberEntity> memberEntity = memberRepository.findById(memberId);
    MemberDto memberDto = MemberConstructor.memberEntityToDto(memberEntity.get());

    return memberDto;

  }
}
