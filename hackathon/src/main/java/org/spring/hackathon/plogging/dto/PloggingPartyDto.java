package org.spring.hackathon.plogging.dto;

import lombok.*;
import org.spring.hackathon.member.domain.MemberEntity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PloggingPartyDto {

  private Long ploggingPartyNo;

  private String partyOwner;

  private String partyName;

  private String partyPlace;

  private int partyEntry;

  private String partyIntro;

  private String filePath;

  //참가자 목록 데이터를 불러올 때 사용
  private MemberEntity partyMember;

}
