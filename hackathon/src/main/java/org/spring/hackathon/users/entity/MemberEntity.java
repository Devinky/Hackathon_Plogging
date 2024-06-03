package org.spring.hackathon.users.entity;

import lombok.*;
import org.spring.hackathon.plogging.entity.PloggingPartyEntity;
import org.spring.hackathon.plogging.entity.PloggingRecordEntity;
import org.spring.hackathon.security.role.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity extends BaseEntity {

  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "memberNo")
  public Long memberNo;
  
  //회원 권한
  @Enumerated(EnumType.STRING)
  private Role role;

  //회원 아이디
  @Column(nullable = false)
  private String memberId;

  //회원 비밀번호
  @Column(nullable = false)
  private String memberPassword;

  //회원 이름(닉네임)
  @Column(nullable = false)
  private String memberName;

  //회원 이메일
  @Column(nullable = false)
  private String memberEmail;

  //회원 주소
  @Column(nullable = false)
  private String memberAddress;

  //자기소개
  @Column(length = 5000)
  private String memberIntro;

  //플로깅 포인트
  private int ploggingPoint;

  //플로깅 기록 테이블과 연관 매핑(1:N 관계)
  @OneToMany(mappedBy = "recordJoinMember", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<PloggingRecordEntity> ploggingRecordEntities = new ArrayList<>();

  //회원과 플로깅 방의 N:M 관계를 해소해주는 연결 테이블 생성, 매핑
  @OneToMany
  @JoinTable(name = "party_member_connect", joinColumns = @JoinColumn(name = "member_no"),
  inverseJoinColumns = @JoinColumn(name = "plogging_party_no"))
  private List<PloggingPartyEntity> ploggingPartyEntities = new ArrayList<>();

}
