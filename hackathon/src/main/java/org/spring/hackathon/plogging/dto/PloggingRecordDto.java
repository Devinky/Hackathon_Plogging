package org.spring.hackathon.plogging.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PloggingRecordDto {

  private Long ploggingRecordNo;

  private int ploggingDistance;

  private ArrayList<String> trashCategory;

  private LocalDateTime ploggingDate;

  private String ploggingTime;

  //아래로 사진 관련 Dto
  //플로깅 기록에 사진이 포함됐을 때, 사진을 저장하기 위한 객체
  private File ploggingPhoto;

  private String ploggingPhotoNameOrigin;

  private String ploggingPhotoNameNew;

  private int recordAttachPhoto;

}
