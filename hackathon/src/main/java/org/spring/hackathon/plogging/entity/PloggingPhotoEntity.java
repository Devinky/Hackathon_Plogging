package org.spring.hackathon.plogging.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.hackathon.users.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ploggingPhoto")
public class PloggingPhotoEntity extends BaseEntity {

  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ploggingPhotoNo")
  public Long ploggingPhotoNo;

  //원본 이미지 파일명
  private String ploggingPhotoNameOrigin;

  //저장될 이미지 파일명(이미지가 저장소에 저장될 때 새로운 이름으로 바꿔줘야 해서 필요합니다)
  private String ploggingPhotoNameNew;

  //플로깅 기록 테이블과 연관 매핑(N:1 관계)
  @ManyToOne
  @JoinColumn(name = "plogging_record_pk")
  private PloggingRecordEntity photoJoinRecord;

}
