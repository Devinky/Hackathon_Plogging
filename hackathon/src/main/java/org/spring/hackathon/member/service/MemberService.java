package org.spring.hackathon.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.member.constructor.MemberConstructor;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  //마이페이지 회원 정보 조회
  public MemberDto memberMyPageView(Long memberNo) {

    Optional<MemberEntity> memberEntity = memberRepository.findById(memberNo);
    MemberDto memberDto = MemberConstructor.memberEntityToDto(memberEntity.get());

    return memberDto;

  }

  //회원 정보 수정
  @Transactional
  public MemberDto memberInfoUpdate(Long memberNo, MemberDto dto) {

    Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberNo);

    if (optionalMemberEntity.isPresent()){

      MemberEntity memberUpdateEntity = optionalMemberEntity.get();

      memberUpdateEntity.setMemberPassword(passwordEncoder.encode(dto.getMemberPassword()));
      memberUpdateEntity.setMemberName(dto.getMemberName());
      memberUpdateEntity.setMemberEmail(dto.getMemberEmail());
      memberUpdateEntity.setMemberAddress(dto.getMemberAddress());
      memberUpdateEntity.setMemberIntro(dto.getMemberIntro());
      memberUpdateEntity.setMemberAttachPhoto(dto.getMemberAttachPhoto());

      memberRepository.save(memberUpdateEntity);

      }

    return null;

    }
   
  //회원 탈퇴
  public void memberDelete(Long memberNo) {

    memberRepository.deleteById(memberNo);
    
  }

}
