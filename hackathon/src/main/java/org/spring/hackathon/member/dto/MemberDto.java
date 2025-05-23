package org.spring.hackathon.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

  public Long memberKey;

  private String memberId;

  private String memberPassword;

  private String memberName;

  private String memberEmail;

  private String memberAddress;

  private String memberIntro;

  private int ploggingPoint;

  private double ploggingDistanceTotal;

}
