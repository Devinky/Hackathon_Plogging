package org.spring.hackathon.plogging.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PloggingRecordDto {

  private Long ploggingRecordNo;

  private double ploggingDistance;

  private ArrayList<String> trashCategory;

  private LocalDate ploggingDate;

  private String ploggingTime;

  private int recordAttachImage;

}
