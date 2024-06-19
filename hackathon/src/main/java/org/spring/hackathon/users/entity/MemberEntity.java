package org.spring.hackathon.users.entity;

import lombok.*;
import org.spring.hackathon.baseTime.BaseEntity;
import org.spring.hackathon.plogging.entity.PloggingPartyEntity;
import org.spring.hackathon.plogging.entity.PloggingPartyReplyEntity;
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
  @Column(name = "member_no")
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

  //회원 프로필 사진 첨부 여부(첨부 = 1, 첨부X = 0)
  @Column(nullable = false)
  private int memberAttachPhoto;

  //자기소개
  @Column(length = 5000)
  private String memberIntro;
  
  //총 플로깅 포인트
  @Column(nullable = false)
  private int ploggingPoint;

  //총 플로깅 거리
  @Column(nullable = false)
  private int ploggingDistanceTotal;

  //Join 관계들
  //플로깅 기록 테이블과 매핑(1:N 관계)
  @OneToMany(mappedBy = "recordJoinMember", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<PloggingRecordEntity> ploggingRecordList = new ArrayList<>();

  //단체 플로깅 모임 테이블과 매핑(1:N 관계) / **종속관계 정립하기(방장이 탈퇴해도, 모임방은 사라지지 않음)**
  @OneToMany(mappedBy = "partyJoinMember")
  private List<PloggingPartyEntity> partyListForLeader = new ArrayList<>();

  //회원과 플로깅 방의 N:M 관계를 해소해주는 연결 테이블 생성, 매핑
  @OneToMany
  @JoinTable(name = "party_member_connect", joinColumns = @JoinColumn(name = "member_party_fk"),
  inverseJoinColumns = @JoinColumn(name = "plogging_party_fk"))
  private List<PloggingPartyEntity> partyList = new ArrayList<>();

  //플로깅 단체방에 남길 수 있는 댓글과 매핑
  @OneToMany(mappedBy = "replyWriter")
  private List<PloggingPartyReplyEntity> partyReplyListForMember = new ArrayList<>();

}
