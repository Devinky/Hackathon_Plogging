package org.spring.hackathon.plogging.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.repository.MemberRepository;
import org.spring.hackathon.plogging.constructor.PloggingRecordConstructor;
import org.spring.hackathon.plogging.domain.PloggingImageEntity;
import org.spring.hackathon.plogging.domain.PloggingLocationEntity;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.spring.hackathon.plogging.dto.PloggingLocationDto;
import org.spring.hackathon.plogging.dto.PloggingRecordAndCoordinateDto;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;
import org.spring.hackathon.plogging.repository.PloggingImageRepository;
import org.spring.hackathon.plogging.repository.PloggingLocationRepository;
import org.spring.hackathon.plogging.repository.PloggingRecordRepository;
import org.spring.hackathon.security.utils.AuthorizationValidate;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PloggingViewService {
  private final MemberRepository memberRepository;

  private final PloggingRecordRepository recordRepository;
  private final PloggingLocationRepository locationRepository;
  private final PloggingImageRepository imageRepository;
  private final AuthorizationValidate authorizationValidate;

  //플로깅 기록 조회(기본 접근)
  public List<PloggingRecordDto> recordThisMonthView(String token, Long memberKey) {

    authorizationValidate.tokenValidate(memberKey, token);

    LocalDate today = LocalDate.now();
    LocalDate startDate = today.withDayOfMonth(1);
    LocalDate lastDate = today.withDayOfMonth(today.lengthOfMonth());

    log.info("오늘 : " + today);
    log.info("시작 날짜 : " + startDate);
    log.info("끝 날짜 : " + lastDate);

    List<PloggingRecordEntity> recordEntityList = recordRepository.findByIdAndMonth(memberKey, startDate, lastDate);

    List<PloggingRecordDto> recordDtoList = recordEntityList.stream()
        .map(PloggingRecordConstructor::ploggingRecordMonthView)
        .collect(Collectors.toList());

    return recordDtoList;

  }

  //플로깅 기록 조회(선택한 달력)
  public List<PloggingRecordDto> recordOtherMonthView(String token, Long memberKey, YearMonth selectDate) {

    authorizationValidate.tokenValidate(memberKey, token);

    LocalDate startDate = selectDate.atDay(1);
    LocalDate lastDate = selectDate.atEndOfMonth();

    log.info("선택 날짜 : " + selectDate);
    log.info("시작 날짜 : " + startDate);
    log.info("끝 날짜 : " + lastDate);

    List<PloggingRecordEntity> recordEntityList = recordRepository.findByIdAndMonth(memberKey, startDate, lastDate);

    List<PloggingRecordDto> recordDtoList = recordEntityList.stream()
        .map(PloggingRecordConstructor::ploggingRecordMonthView)
        .collect(Collectors.toList());

    return recordDtoList;

  }

  //플로깅 기록 상세조회
  public PloggingRecordAndCoordinateDto recordDetailView(String token, Long memberKey, Long recordKey) {

    authorizationValidate.tokenValidate(memberKey, token);

    List<PloggingLocationEntity> locationEntityList = locationRepository.findAllByPloggingRecordForeignKey(recordKey);

    List<PloggingLocationDto> locationDtoList = locationEntityList.stream()
            .map(PloggingRecordConstructor::coordinateGet)
            .collect(Collectors.toList());

    Optional<PloggingRecordEntity> recordCheck = recordRepository.findById(recordKey);
    PloggingRecordEntity recordGet = recordCheck.get();

    String filePath = "";

    if(recordGet.getPloggingRecordAttachImage() == 1) {

      PloggingImageEntity imageCheck = imageRepository.findByRecordForeignKey(recordKey);
      filePath = imageCheck.getFilePath();

    }

    PloggingRecordAndCoordinateDto recordAndCoordinate =
        PloggingRecordConstructor.recordAndCoordinateGet(recordGet, locationDtoList, filePath);

    return recordAndCoordinate;

  }

  //플로깅 기록 삭제
  @Transactional
  public void ploggingRecordDelete(String token, Long memberKey, Long recordKey) {

    authorizationValidate.tokenValidate(memberKey, token);

   //이 사이에 회원 테이블에 기록된 총 거리와 포인트 계산 처리 해야함
    Optional<PloggingRecordEntity> recordCheck = recordRepository.findById(recordKey);
    PloggingRecordEntity recordGet = recordCheck.get();

    double calcDistance = Math.floor(recordGet.getPloggingDistance() * 100) / 100.0;
    log.info("calcDistance : " + calcDistance);

    Optional<MemberEntity> memberCheck = memberRepository.findById(memberKey);
    MemberEntity memberGet = memberCheck.get();

    double reCalcDistance = memberGet.getPloggingDistanceTotal() - calcDistance;
    int reCalcPoint = memberGet.getPloggingPoint() - recordGet.getGetPoint();

    memberGet.setPloggingDistanceTotal(reCalcDistance);
    memberGet.setPloggingPoint(reCalcPoint);

    memberRepository.save(memberGet);
    recordRepository.deleteById(recordKey);

  }

}
