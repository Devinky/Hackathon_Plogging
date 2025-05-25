package org.spring.hackathon.security.utils;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorizationValidate {

  private final JwtProvider jwtProvider;
  private final MemberRepository memberRepository;

  public MemberEntity tokenValidate(Long memberKey, String token) {

    Optional<MemberEntity> memberCheck = memberRepository.findById(memberKey);
    MemberEntity memberData = memberCheck.get();

    String memberId = jwtProvider.getUserId(token.substring(7));

    if(!memberCheck.isPresent()) {

      throw new RuntimeException("정상적인 접근이 아닙니다(회원 확인 불가)");

    } else if (!memberData.getMemberId().equals(memberId)) {

      throw new RuntimeException("정상적인 접근이 아닙니다(로그인 정보 불일치)");

    }

    return memberData;

  }

}
