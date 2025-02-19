package org.spring.hackathon.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

  public Long memberNo;

  private String memberId;

  private String memberPassword;

  private String memberName;

  private String memberEmail;

  private String memberAddress;

  private int memberAttachImage;

  private String memberIntro;

  private int ploggingPoint;

  private float ploggingDistanceTotal;

}
