package org.spring.hackathon.plogging.constructor;

import org.spring.hackathon.plogging.domain.PloggingImageEntity;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.springframework.web.multipart.MultipartFile;

public class PloggingImageConstructor {
  public static PloggingImageEntity ploggingImageTransfer(MultipartFile image, String newImageName, String filePath, PloggingRecordEntity recordEntity) {

    PloggingImageEntity recordImage = new PloggingImageEntity();

    recordImage.setPloggingImageNameOrigin(image.getOriginalFilename());
    recordImage.setPloggingImageNameNew(newImageName);
    recordImage.setFilePath(filePath);
    recordImage.setRecordJoinImage(recordEntity);

    return recordImage;

  }
}
