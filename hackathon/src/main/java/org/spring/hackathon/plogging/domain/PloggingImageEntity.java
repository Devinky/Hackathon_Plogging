package org.spring.hackathon.plogging.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.hackathon.common.baseTime.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plogging_image")
public class PloggingImageEntity extends BaseEntity {

  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "plogging_image_key")
  public Long ploggingImageKey;

  //원본 이미지 파일명
  private String ploggingImageNameOrigin;

  //저장될 이미지 파일명(이미지가 저장소에 저장될 때 중복 방지를 위해 새로운 이름으로 바꿈)
  private String ploggingImageNameNew;

  private String filePath;

  //개인플로깅 테이블과 연관 매핑(N:1 관계)
  @ManyToOne
  @JoinColumn(name = "plogging_record_fk")
  private PloggingRecordEntity recordJoinImage;

}
