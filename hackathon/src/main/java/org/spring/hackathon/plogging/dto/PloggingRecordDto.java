package org.spring.hackathon.plogging.dto;

import lombok.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

  private int recordAttachImage;

}
