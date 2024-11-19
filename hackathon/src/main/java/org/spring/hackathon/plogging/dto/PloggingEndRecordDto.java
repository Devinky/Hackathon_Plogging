package org.spring.hackathon.plogging.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@ToString
public class PloggingEndRecordDto {

  private ArrayList<String> trashCategory;

  private LocalDateTime ploggingDate;

  private String ploggingTime;

  private File ploggingPhoto;

  private String ploggingPhotoNameOrigin;

  private String ploggingPhotoNameNew;

  private int recordAttachPhoto;

}
