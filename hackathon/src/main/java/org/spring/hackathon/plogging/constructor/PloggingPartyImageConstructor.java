package org.spring.hackathon.plogging.constructor;

import org.spring.hackathon.plogging.domain.PloggingPartyEntity;
import org.spring.hackathon.plogging.domain.PloggingPartyImageEntity;
import org.springframework.web.multipart.MultipartFile;

public class PloggingPartyImageConstructor {

  public static PloggingPartyImageEntity PloggingPartyImageTransfer(MultipartFile image, String newImageName, String filePath, PloggingPartyEntity partyEntity) {

    PloggingPartyImageEntity partyImage = new PloggingPartyImageEntity();

    partyImage.setPartyImageOriginName(image.getOriginalFilename());
    partyImage.setPartyImageNewName(newImageName);
    partyImage.setFilePath(filePath);
    partyImage.setPartyJoinImage(partyEntity);

    return partyImage;

  }
}
