package org.spring.hackathon.member.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class SignFileService {

  @Transactional
  public void fileSave(MultipartFile memberFile) {

  }
}
