package org.spring.hackathon.users.entity;

import lombok.*;
import org.spring.hackathon.baseTime.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "memberPhoto")
public class MemberPhotoEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(table = "memberPhoto")
  private Long memberPhotoNo;

}
