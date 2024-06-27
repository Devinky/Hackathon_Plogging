package org.spring.hackathon.plogging.dto;

import lombok.*;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PloggingPhotoDto {

  public Long ploggingPhotoNo;

  //사용자가 지정해놓은 파일 이름
  private String ploggingPhotoNameOrigin;

  //새로 저장될 파일 이름
  private String ploggingPhotoNameNew;

  private PloggingRecordEntity photoJoinRecord;

}
