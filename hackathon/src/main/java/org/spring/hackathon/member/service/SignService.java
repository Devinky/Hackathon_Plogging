package org.spring.hackathon.member.service;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.member.constructor.MemberConstructor;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.repository.MemberRepository;
import org.spring.hackathon.security.exception.AppException;
import org.spring.hackathon.security.exception.ErrorCode;
import org.spring.hackathon.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${jwt.secret}")
  private String secretKey;
  private Long expiredMs = 1000 * 60 * 60 * 10L;

  //회원가입
  @Transactional
  public String signUp(MemberDto dto) {

    String memberId = dto.getMemberId();

    //아이디 중복 Check -> DB 확인
    memberRepository.findByMemberId(memberId).ifPresent(user -> {
      throw new AppException(ErrorCode.MEMBERID_DUPLICATED, "'" + memberId + "'" + " 는 이미 사용 중인 아이디입니다.");
    });

    //ID 중복체크 통과하면
    //Dto -> Entity 변환자를 이용하여 Entity에 정보를 Set
    MemberEntity memberEntity = MemberConstructor.memberDtoToEntity(dto, passwordEncoder);
    //회원정보를 최종적으로 Repository에 저장
    memberRepository.save(memberEntity);

    return "회원가입 완료";

  }
  //로그인
  @Transactional
  public String signIn(String id, String password) {

    //ID가 존재하지 않을 때
    MemberEntity selectedMember = memberRepository.findByMemberId(id)
        .orElseThrow(() -> new AppException(ErrorCode.MEMBERID_NOT_FOUND, id + " 로그인 실패 : 해당 ID 찾을 수 없음"));

    //비밀번호 틀렸을 때
    if(passwordEncoder.matches(selectedMember.getMemberPassword(), password)) {
      System.out.println("암호 : " + selectedMember.getMemberPassword() + " / 비밀번호 : " + password);
      throw new AppException(ErrorCode.INVALID_PASSWORD, "로그인 실패 : 비밀번호 불일치");
    }

    //모든 Exception을 통과했을 때 토큰을 발행
    return JwtUtil.createToken(id, secretKey, expiredMs);

  }

}
