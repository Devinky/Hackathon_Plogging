package org.spring.hackathon.member.constructor;

import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.security.role.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberConstructor {

  //회원가입할 때 받은 Dto의 데이터를 Entity에 저장하는 용도
  public static MemberEntity memberDtoToEntity(MemberDto memberDto, PasswordEncoder passwordEncoder) {

    MemberEntity memberEntity = new MemberEntity();

    memberEntity.setMemberId(memberDto.getMemberId());
    memberEntity.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
    memberEntity.setMemberName(memberDto.getMemberName());
    memberEntity.setMemberEmail(memberDto.getMemberEmail());
    memberEntity.setMemberAddress(memberDto.getMemberAddress());
    memberEntity.setMemberAttachPhoto(memberDto.getMemberAttachPhoto());
    memberEntity.setMemberIntro(memberDto.getMemberIntro());
    memberEntity.setPloggingPoint(0);
    memberEntity.setPloggingDistanceTotal(0);
    memberEntity.setRole(Role.MEMBER);

    return memberEntity;

  }

  //로그인 요청시 사용
  public static MemberDto signUpRequest(MemberEntity memberEntity) {

    MemberDto memberDto = new MemberDto();

    memberDto.setMemberId(memberEntity.getMemberId());
    memberDto.setMemberPassword(memberEntity.getMemberPassword());

    return memberDto;

  }

  //DB에 저장된 회원 데이터를 가져오는 용도(토큰 발급 및 마이페이지 정보 조회용)
  public static MemberDto memberEntityToDto(MemberEntity memberEntity) {

    MemberDto memberDto = new MemberDto();

    memberDto.setMemberId(memberEntity.getMemberId());
    memberDto.setMemberEmail(memberEntity.getMemberEmail());
    memberDto.setMemberName(memberEntity.getMemberName());
    memberDto.setMemberAddress(memberEntity.getMemberAddress());
    memberDto.setMemberIntro(memberEntity.getMemberIntro());
    memberDto.setMemberAttachPhoto(memberEntity.getMemberAttachPhoto());
    memberDto.setPloggingPoint(memberEntity.getPloggingPoint());
    memberDto.setPloggingDistanceTotal(memberEntity.getPloggingDistanceTotal());

    return memberDto;
  }

}
