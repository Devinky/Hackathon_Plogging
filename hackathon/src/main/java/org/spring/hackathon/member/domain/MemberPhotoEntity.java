package org.spring.hackathon.member.domain;

import lombok.*;
import org.spring.hackathon.baseTime.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_photo")
public class MemberPhotoEntity extends BaseEntity {

  //회원 테이블과 1:1 매핑

  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_photo_no")
  private Long memberPhotoNo;

  //사진 원본 이름
  private String memberPhotoNameOrigin;

  //사진 변환된 이름
  private String memberPhotoNameNew;

  @OneToOne
  @JoinColumn(name = "member_fk")
  private MemberEntity memberJoinPhoto;

}
