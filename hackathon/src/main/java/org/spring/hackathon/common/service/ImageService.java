package org.spring.hackathon.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.member.constructor.MemberImageConstructor;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.domain.MemberImageEntity;
import org.spring.hackathon.member.repository.MemberImageRepository;
import org.spring.hackathon.member.repository.MemberRepository;
import org.spring.hackathon.plogging.constructor.PloggingImageConstructor;
import org.spring.hackathon.plogging.constructor.PloggingPartyImageConstructor;
import org.spring.hackathon.plogging.domain.PloggingImageEntity;
import org.spring.hackathon.plogging.domain.PloggingPartyEntity;
import org.spring.hackathon.plogging.domain.PloggingPartyImageEntity;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.spring.hackathon.plogging.repository.PloggingImageRepository;
import org.spring.hackathon.plogging.repository.PloggingPartyImageRepository;
import org.spring.hackathon.plogging.repository.PloggingPartyRepository;
import org.spring.hackathon.plogging.repository.PloggingRecordRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class ImageService {

  private final MemberRepository memberRepository;
  private final MemberImageRepository memberImageRepository;
  private final PloggingRecordRepository ploggingRecordRepository;
  private final PloggingImageRepository ploggingImageRepository;
  private final PloggingPartyRepository ploggingPartyRepository;
  private final PloggingPartyImageRepository ploggingPartyImageRepository;

//  @Value("${spring.servlet.location}")
  private static String UPLOAD_DIR = "C:\\Users\\devin\\Documents\\ploggingImages";

  @Transactional
  public void imageSave(MultipartFile image, String identify, Long identifyNo) throws IOException {

    String newImageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
    String filePath = UPLOAD_DIR + newImageName;

    log.info("파일 URL : " + filePath);

    image.transferTo(new File(filePath));

    if (identify == "member") {

      Optional<MemberEntity> memberCheck = memberRepository.findById(identifyNo);
      MemberEntity memberEntity = memberCheck.get();

      MemberImageEntity memberImage = MemberImageConstructor.memberImageTransfer(image, newImageName, filePath, memberEntity);

      memberImageRepository.save(memberImage);

    } else if (identify == "plogging") {

      Optional<PloggingRecordEntity> recordCheck = ploggingRecordRepository.findById(identifyNo);
      PloggingRecordEntity recordEntity = recordCheck.get();

      PloggingImageEntity recordImage = PloggingImageConstructor.ploggingImageTransfer(image, newImageName, filePath, recordEntity);

      ploggingImageRepository.save(recordImage);

    } else if (identify == "party") {

      Optional<PloggingPartyEntity> partyCheck = ploggingPartyRepository.findById(identifyNo);
      PloggingPartyEntity partyEntity = partyCheck.get();

      PloggingPartyImageEntity partyImage = PloggingPartyImageConstructor.PloggingPartyImageTransfer(image, newImageName, filePath, partyEntity);

      ploggingPartyImageRepository.save(partyImage);
      
    }

  }
}
