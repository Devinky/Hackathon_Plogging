package org.spring.hackathon.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.member.constructor.MemberConstructor;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.repository.MemberImageRepository;
import org.spring.hackathon.member.repository.MemberRepository;
import org.spring.hackathon.security.utils.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final JwtProvider jwtProvider;
  private final MemberRepository memberRepository;
  private final MemberImageRepository memberImageRepository;
  private final PasswordEncoder passwordEncoder;

  //마이페이지 회원 정보 조회
  public MemberDto memberMyPageView(Long memberNo, String token) {

    Optional<MemberEntity> memberCheck = memberRepository.findById(memberNo);
    MemberEntity memberEntityGet = memberCheck.get();

    String memberId = jwtProvider.getUserId(token.substring(7));

    if(!memberCheck.isPresent()) {
      throw new RuntimeException("정상적인 접근이 아닙니다(회원 확인 불가)");
    }

    if(!memberEntityGet.getMemberId().equals(memberId)) {
      throw new RuntimeException("정상적인 접근이 아닙니다(로그인 정보 불일치)");
    }

    if(memberEntityGet.getMemberAttachImage() == 1) {

      String filePath = memberImageRepository.findFilePathWithMemberFk(memberNo);
      MemberDto memberDto = MemberConstructor.memberEntityToDto(memberEntityGet, filePath);

      return memberDto;

    } else {

      MemberDto memberDto = MemberConstructor.memberEntityToDto(memberEntityGet, null);
      return memberDto;

    }

  }

  //회원 정보 수정
  @Transactional
  public MemberDto memberInfoUpdate(Long memberNo, MemberDto dto, String token) {

    Optional<MemberEntity> memberCheck = memberRepository.findById(memberNo);
    MemberEntity memberEntityGet = memberCheck.get();

    String memberId = jwtProvider.getUserId(token.substring(7));

    if(!memberCheck.isPresent()) {
      throw new RuntimeException("정상적인 접근이 아닙니다(회원 확인 불가)");
    }

    if(!memberEntityGet.getMemberId().equals(memberId)) {
      throw new RuntimeException("정상적인 접근이 아닙니다(로그인 정보 불일치)");
    }

    if (memberCheck.isPresent()){

      memberEntityGet.setMemberPassword(passwordEncoder.encode(dto.getMemberPassword()));
      memberEntityGet.setMemberName(dto.getMemberName());
      memberEntityGet.setMemberEmail(dto.getMemberEmail());
      memberEntityGet.setMemberAddress(dto.getMemberAddress());
      memberEntityGet.setMemberIntro(dto.getMemberIntro());
      memberEntityGet.setMemberAttachImage(dto.getMemberAttachImage());

      memberRepository.save(memberEntityGet);

      }

    return null;

    }
   
  //회원 탈퇴
  public void memberDelete(Long memberNo, String token) {

    Optional<MemberEntity> memberCheck = memberRepository.findById(memberNo);
    MemberEntity memberEntityGet = memberCheck.get();

    String memberId = jwtProvider.getUserId(token.substring(7));

    if(!memberCheck.isPresent()) {
      throw new RuntimeException("정상적인 접근이 아닙니다(회원 확인 불가)");
    }

    if(!memberEntityGet.getMemberId().equals(memberId)) {
      throw new RuntimeException("정상적인 접근이 아닙니다(로그인 정보 불일치)");
    }

    memberRepository.deleteById(memberNo);
    
  }

}
