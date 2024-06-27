package org.spring.hackathon.plogging.domain;

import lombok.*;
import org.spring.hackathon.baseTime.BaseEntity;
import org.spring.hackathon.member.domain.MemberEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plogging_party")
public class PloggingPartyEntity extends BaseEntity {
  
  //단체플로깅 기록 테이블

  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "plogging_party_no")
  public Long partyNo;

  //방 이름
  @Column(nullable = false)
  private String partyName;

  //진행 장소
  @Column(nullable = false)
  private String partyPlace;

  //예정 시간
  //aa = am, pm 표기
  @DateTimeFormat(pattern = "yyyy-MM-dd HH aa")
  private SimpleDateFormat partyDate;

  //참여 인원 수
  @Column(nullable = false)
  private int partyEntry;

  //방 소개
  @Column(length = 5000)
  private String partyIntro;

  //시작 시간
  @Column(nullable = false)
  private LocalTime partyStart;

  //종료 시간
  @Column(nullable = false)
  private LocalTime partyEnd;

  //플로깅 진행 시간
  @Column(nullable = false)
  private String partyTime;

  //사진 첨부 여부
  @Column(nullable = false)
  private int partyAttachPhoto;
  
  //방장 정보 매핑
  @ManyToOne
  @JoinColumn(name = "member_fk")
  private MemberEntity partyJoinMember;

  //댓글 목록 매핑
  @OneToMany(mappedBy = "partyJoinReply")
  private List<PloggingPartyReplyEntity> partyReplyListForParty = new ArrayList<>();
  
  //플로깅 방과 회원 간의 N:M 관계를 해소해주는 연결 테이블 생성, 매핑
  @OneToMany
  @JoinTable(name = "party_member_connect", joinColumns = @JoinColumn(name = "plogging_party_fk"),
  inverseJoinColumns = @JoinColumn(name = "member_party_fk"))
  private List<MemberEntity> memberEntities = new ArrayList<>();


}
