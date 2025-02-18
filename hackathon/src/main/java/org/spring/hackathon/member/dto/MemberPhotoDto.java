package org.spring.hackathon.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberPhotoDto {

  public Long memberPhotoNo;

  private String memberPhotoNameOrigin;

  private String memberPhotoNameNew;

}
