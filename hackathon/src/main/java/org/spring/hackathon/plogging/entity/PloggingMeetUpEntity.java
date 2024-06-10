package org.spring.hackathon.plogging.entity;

import lombok.*;
import org.spring.hackathon.users.entity.BaseEntity;
import org.spring.hackathon.users.entity.MemberEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ploggingParty")
public class PloggingMeetUpEntity extends BaseEntity {

  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ploggingPartyNo")
  public Long partyNo;

  //방장 이름
  @Column(nullable = false)
  private String partyOwner;

  //방 이름
  @Column(nullable = false)
  private String partyName;

  //진행 장소
  @Column(nullable = false)
  private String partyPlace;

  //시간
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm aa")
  private SimpleDateFormat partyDate;

  //참여 인원
  @Column(nullable = false)
  private int partyEntry;

  //방 소개
  @Column(length = 5000)
  private String partyIntro;

  //플로깅 방과 회원 간의 N:M 관계를 해소해주는 연결 테이블 생성, 매핑
  @OneToMany
  @JoinTable(name = "party_member_connect", joinColumns = @JoinColumn(name = "plogging_party_no"),
  inverseJoinColumns = @JoinColumn(name = "member_no"))
  private List<MemberEntity> memberEntities = new ArrayList<>();

}
