package org.spring.hackathon.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ploggingRecord")
public class PloggingRecordEntity extends BaseEntity {

  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PloggingRecordNo")
  public Long recordNo;

  //플로깅 한 거리
  @Column(nullable = false)
  private int ploggingDistance;

  //플로깅 기록에 입력하는 주운 쓰레기 종류(여러 종류 받을 수 있는 거 같아서 리스트 타입으로 지정했어요)
  private ArrayList<String> trashCategory;

  //플로깅 한 날짜
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime ploggingDate;

  //플로깅을 몇 시간 했는지
  private String ploggingTime;

  //이미지 첨부 여부 구분을 위한 컬럼(이미지 포함O = 1, 포함X = 0)
  @Column(nullable = false)
  private int recordAttachPhoto;

  //플로깅 이미지 테이블과 연관 매핑(1:N 관계)
  @OneToMany(mappedBy = "photoJoinRecord", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<PloggingPhotoEntity> ploggingPhotoEntities = new ArrayList<>();

  //회원 테이블과 연관 매핑(N:1 관계)
  @ManyToOne
  @JoinColumn(name = "member_pk")
  private MemberEntity recordJoinMember;

}
