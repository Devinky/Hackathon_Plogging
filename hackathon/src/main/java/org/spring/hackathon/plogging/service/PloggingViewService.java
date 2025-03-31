package org.spring.hackathon.plogging.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.plogging.constructor.PloggingRecordConstructor;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;
import org.spring.hackathon.plogging.repository.PloggingRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PloggingViewService {

  private final PloggingRecordRepository recordRepository;

  public List<PloggingRecordDto> recordReadView(Long memberNo) {

    LocalDate today = LocalDate.now();
    LocalDate startDate = today.withDayOfMonth(1);
    LocalDate endDate = today.withDayOfMonth(today.lengthOfMonth());

    log.info("오늘 : " + today);
    log.info("시작 날짜 : " + startDate);
    log.info("끝 날짜 : " + endDate);

    List<PloggingRecordEntity> recordEntityList = recordRepository.findByIdAndMonth(memberNo, startDate, endDate);

    List<PloggingRecordDto> recordDtoList = recordEntityList.stream()
        .map(PloggingRecordConstructor::ploggingRecordMonthView)
        .collect(Collectors.toList());

    return recordDtoList;

  }

}
