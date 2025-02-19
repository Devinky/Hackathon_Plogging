package org.spring.hackathon.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberImageDto {

  public Long memberImageNo;

  private String memberImageNameOrigin;

  private String memberImageNameNew;

}
