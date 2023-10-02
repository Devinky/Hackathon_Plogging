package org.spring.hackathon.service;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.exception.AppException;
import org.spring.hackathon.exception.ErrorCode;
import org.spring.hackathon.constructor.MemberConstructor;
import org.spring.hackathon.dto.MemberDto;
import org.spring.hackathon.entity.MemberEntity;
import org.spring.hackathon.repository.MemberRepository;
import org.spring.hackathon.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${jwt.secret}")
  private String secretKey;
  private Long expiredMs = 1000 * 60 * 60 * 10L;

  //회원가입
  @Transactional
  public String signup(MemberDto dto) {

    String memberId = dto.getMemberId();

    //아이디 중복 Check -> DB 확인
    memberRepository.findByMemberId(memberId).ifPresent(user -> {
      throw new AppException(ErrorCode.MEMBERID_DUPLICATED, "'" + memberId + "'" + " 는 이미 사용 중인 아이디입니다.");
    });

    //Dto -> Entity 변환자를 이용하여 Entity에 정보를 Set
    MemberEntity memberEntity = MemberConstructor.memberDtoToEntity(dto, passwordEncoder);
    //회원정보를 최종적으로 Repository에 저장
    memberRepository.save(memberEntity);

    return "회원가입 처리 완료";

  }
  //로그인
  @Transactional
  public String signin(String id, String password) {

    //ID가 존재하지 않을 때
    MemberEntity selectedMember = memberRepository.findByMemberId(id)
        .orElseThrow(() -> new AppException(ErrorCode.MEMBERID_NOT_FOUND, id + " 일치하는 회원을 찾을 수 없습니다. ID를 확인해주세요."));

    //비밀번호 틀렸을 때
    if(!passwordEncoder.matches(selectedMember.getMemberPassword(), password)) {
      throw new AppException(ErrorCode.INVALID_PASSWORD, "비밀번호를 확인해주세요.");
    }

    //모든 Exception을 통과했을 때 토큰을 발행
    return JwtUtil.createToken(id, secretKey, expiredMs);

  }

}
