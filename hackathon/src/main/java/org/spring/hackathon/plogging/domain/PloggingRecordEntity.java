package org.spring.hackathon.plogging.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.spring.hackathon.common.baseTime.BaseEntity;
import org.spring.hackathon.member.domain.MemberEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "plogging_record")
public class PloggingRecordEntity extends BaseEntity {

  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "plogging_record_no")
  public Long recordNo;

  //플로깅 한 거리
  private double ploggingDistance;

  //플로깅 기록에 입력하는 주운 쓰레기 종류(여러 종류 받을 수 있는 거 같아서 리스트 타입으로 지정함)
  private ArrayList<String> trashCategory;

  //플로깅 한 날짜
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate ploggingDate;

  //플로깅을 몇시간 했는지
  private String ploggingTime;

  private String ploggingMemo;

  //이미지 첨부 여부 구분을 위한 컬럼(이미지 포함O = 1, 포함X = 0)
  @Column(nullable = false)
  private int ploggingRecordAttachImage;

  //Join 관계들
  //플로깅 이미지 테이블과 연관 매핑(1:N 관계)
  @OneToMany(mappedBy = "recordJoinImage", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<PloggingImageEntity> ploggingImageList = new ArrayList<>();

  //회원 테이블과 연관 매핑(N:1 관계)
  @ManyToOne
  @JoinColumn(name = "member_record_fk")
  private MemberEntity recordJoinMember;

  //플로깅 중 업데이트 되는 위치 좌표를 저장하는 테이블과 연관 매핑(1:N 관계)
  @OneToMany(mappedBy = "locationJoinRecord", cascade =  CascadeType.REMOVE, orphanRemoval = true)
  private List<PloggingLocationEntity> ploggingLocationList = new ArrayList<>();

}
