package org.spring.hackathon.member.dto;

import lombok.*;
import org.spring.hackathon.member.domain.MemberEntity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberImageDto {

  public Long memberImageNo;

  private String memberImageNameOrigin;

  private String memberImageNameNew;

  private MemberEntity imageJoinMember;

}
