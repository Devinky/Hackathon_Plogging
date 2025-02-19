package org.spring.hackathon.member.domain;

import lombok.*;
import org.spring.hackathon.common.baseTime.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_image")
public class MemberImageEntity extends BaseEntity {

  //회원 테이블과 1:1 매핑

  //기본키
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_image_no")
  private Long memberImageNo;

  //사진 원본 이름
  private String memberImageNameOrigin;

  //사진 변환된 이름
  private String memberImageNameNew;

  @OneToOne
  @JoinColumn(name = "member_fk")
  private MemberEntity memberJoinImage;

}
