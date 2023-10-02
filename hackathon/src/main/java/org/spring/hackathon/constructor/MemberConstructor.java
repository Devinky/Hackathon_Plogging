package org.spring.hackathon.constructor;

import org.spring.hackathon.dto.MemberDto;
import org.spring.hackathon.entity.MemberEntity;
import org.spring.hackathon.role.Role;
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
    memberEntity.setPloggingPoint(2000);
    memberEntity.setRole(Role.MEMBER);

    return memberEntity;

  }

  public static MemberDto signUpRequest(MemberEntity memberEntity) {

    MemberDto memberDto = new MemberDto();

    memberDto.setMemberId(memberEntity.getMemberId());
    memberDto.setMemberPassword(memberEntity.getMemberPassword());

    return memberDto;

  }

  //DB에 저장된 회원 데이터를 가져오는 용도
  public static MemberDto memberEntityToDto(MemberEntity memberEntity) {

    MemberDto memberDto = new MemberDto();

    memberDto.setMemberId(memberEntity.getMemberId());
    memberDto.setMemberEmail(memberEntity.getMemberEmail());
    memberDto.setMemberName(memberEntity.getMemberName());
    memberDto.setMemberAddress(memberEntity.getMemberAddress());
    memberDto.setPloggingPoint(memberEntity.getPloggingPoint());

    return memberDto;
  }

}
