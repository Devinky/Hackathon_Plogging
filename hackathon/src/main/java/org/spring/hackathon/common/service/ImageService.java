package org.spring.hackathon.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {
  @Value("${spring.servlet.location}")
  private static String UPLOAD_DIR;

  @Transactional
  public void imageSave(MultipartFile image, String identify) throws IOException {

    String newImageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
    String filePath = UPLOAD_DIR + newImageName;

    image.transferTo(new File(filePath));

  }
}
