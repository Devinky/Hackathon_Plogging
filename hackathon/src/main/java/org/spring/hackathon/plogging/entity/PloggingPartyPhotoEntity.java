package org.spring.hackathon.plogging.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "plogging_party_photo")
public class PloggingPartyPhotoEntity {

  //플로깅 단체방에서 업로드 된 사진 파일을 저장하는 테이블
  //단체방 메인 사진, 단체플로깅 진행 후 인증 사진 두 종류를 저장한다
  
  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "plogging_party_photo_No")
  private Long partyPhotoNo;
  
  //사진 파일 원본 이름
  private String partyPhotoOriginName;

  //사진 파일 변환된 이름
  private String partyPhotoNewName;

  @ManyToOne
  @JoinColumn(name = "party_photo_fk")
  private PloggingPartyEntity partyJoinPhoto;

}
