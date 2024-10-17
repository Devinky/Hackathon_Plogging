package org.spring.hackathon.plogging.service;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.spring.hackathon.plogging.dto.PloggingLocationDto;
import org.spring.hackathon.plogging.repository.PloggingLocationRepository;
import org.spring.hackathon.plogging.repository.PloggingRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PloggingRecordService {

  private final PloggingRecordRepository ploggingRecordRepository;
  private final PloggingLocationRepository ploggingLocationRepository;

  public Long ploggingStartDo(PloggingLocationDto location) {

    Optional<PloggingRecordEntity> ploggingRecordId = ploggingRecordRepository.findById();

//    return ploggingRecordId;
  }

  public void ploggingLocationUpdate(PloggingLocationDto location, Long ploggingRecordNo) {
  }

  public void ploggingEndDo(Long ploggingRecordNo) {
  }
}
