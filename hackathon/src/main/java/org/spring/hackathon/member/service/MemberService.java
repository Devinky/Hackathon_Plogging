package org.spring.hackathon.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.member.constructor.MemberConstructor;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.repository.MemberRepository;
import org.spring.hackathon.security.utils.AuthorizationValidate;
import org.spring.hackathon.security.utils.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthorizationValidate authorizationValidate;

  //마이페이지 회원 정보 조회
  public MemberDto memberMyPageView(Long memberKey, String token) {

    MemberEntity memberData = authorizationValidate.tokenValidate(memberKey, token);
    MemberDto memberDto = MemberConstructor.memberEntityToDto(memberData);

    return memberDto;

  }

  //회원 정보 수정
  @Transactional
  public MemberDto memberInfoUpdate(Long memberKey, MemberDto memberDto, String token) {

    MemberEntity memberData = authorizationValidate.tokenValidate(memberKey, token);
    MemberEntity memberDataUpdate = MemberConstructor.memberDataUpdate(passwordEncoder, memberData, memberDto);

    memberRepository.save(memberDataUpdate);

    return null;

    }

  //회원 탈퇴
  public void memberDelete(Long memberKey, String token) {

    authorizationValidate.tokenValidate(memberKey, token);

    memberRepository.deleteById(memberKey);
    
  }

}
