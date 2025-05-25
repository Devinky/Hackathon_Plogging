package org.spring.hackathon.plogging.constructor;

import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.plogging.domain.PloggingLocationEntity;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.spring.hackathon.plogging.dto.PloggingLocationDto;
import org.spring.hackathon.plogging.dto.PloggingRecordAndCoordinateDto;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;

import java.time.LocalDate;
import java.util.List;

public class PloggingRecordConstructor {

  //플로깅 시작
  public static PloggingRecordEntity ploggingStartTransfer(MemberEntity memberEntity) {

    PloggingRecordEntity ploggingRecord = new PloggingRecordEntity();

    ploggingRecord.setPloggingDate(LocalDate.now());
    ploggingRecord.setPloggingRecordAttachImage(0);
    ploggingRecord.setRecordJoinMember(memberEntity);

    return ploggingRecord;

  }

  //위치 업데이트
  public static PloggingLocationEntity ploggingLocationTransfer(PloggingLocationDto location, PloggingRecordEntity ploggingRecord) {

    PloggingLocationEntity locationInsert = new PloggingLocationEntity();

    locationInsert.setLatitude(location.getLatitude());
    locationInsert.setLongitude(location.getLongitude());
    locationInsert.setLocationJoinRecord(ploggingRecord);

    return locationInsert;

  }

  //플로깅 종료
  public static PloggingRecordEntity ploggingEndTransfer(PloggingRecordDto recordDto, PloggingRecordEntity finalRecord, int distancePoint) {

    finalRecord.setTrashCategory(recordDto.getTrashCategory());
    finalRecord.setPloggingTime(recordDto.getPloggingTime());
    finalRecord.setPloggingRecordAttachImage(recordDto.getRecordAttachImage());
    finalRecord.setPloggingMemo(recordDto.getPloggingMemo());
    finalRecord.setGetPoint(distancePoint);

    return finalRecord;

  }

  //플로깅 기록 단순 조회
  public static PloggingRecordDto ploggingRecordMonthView(PloggingRecordEntity recordEntity) {

    PloggingRecordDto recordDto = new PloggingRecordDto();

    recordDto.setPloggingRecordKey(recordEntity.getRecordKey());
    recordDto.setPloggingDistance(recordEntity.getPloggingDistance());
    recordDto.setPloggingDate(recordEntity.getPloggingDate());
    recordDto.setPloggingTime(recordEntity.getPloggingTime());
    recordDto.setTrashCategory(recordEntity.getTrashCategory());

    return recordDto;

  }

  //플로깅 기록 상세 조회
  //플로깅에 기록된 위치 좌표 가져오는 용도
  public static PloggingLocationDto coordinateGet(PloggingLocationEntity locationEntity) {

    PloggingLocationDto locationDto = new PloggingLocationDto();

    locationDto.setLatitude(locationEntity.getLatitude());
    locationDto.setLongitude(locationEntity.getLongitude());

    return locationDto;

  }

  //기록과 좌표 한번에 가져오기
  public static PloggingRecordAndCoordinateDto recordAndCoordinateGet(PloggingRecordEntity recordEntity, List<PloggingLocationDto> coordinate, String filePath) {

    PloggingRecordDto recordDto = new PloggingRecordDto();

    recordDto.setPloggingTime(recordEntity.getPloggingTime());
    recordDto.setPloggingDistance(recordEntity.getPloggingDistance());
    recordDto.setPloggingDate(recordEntity.getPloggingDate());
    recordDto.setGetPoint(recordEntity.getGetPoint());
    recordDto.setTrashCategory(recordEntity.getTrashCategory());
    recordDto.setPloggingMemo(recordEntity.getPloggingMemo());
    recordDto.setFilePath(filePath);

    PloggingRecordAndCoordinateDto recordAndCoordinateDto = new PloggingRecordAndCoordinateDto();

    recordAndCoordinateDto.setRecordDto(recordDto);
    recordAndCoordinateDto.setLocationDto(coordinate);

    return recordAndCoordinateDto;

  }

}
