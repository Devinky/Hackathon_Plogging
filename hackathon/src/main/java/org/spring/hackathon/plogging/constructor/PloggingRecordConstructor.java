package org.spring.hackathon.plogging.constructor;

import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.plogging.domain.PloggingLocationEntity;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.spring.hackathon.plogging.dto.PloggingLocationDto;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;

import java.time.LocalDate;

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

  //플로깅 기록 조회
  public static PloggingRecordDto ploggingRecordMonthView(PloggingRecordEntity recordEntity) {

    PloggingRecordDto recordDto = new PloggingRecordDto();

    recordDto.setPloggingDistance(recordEntity.getPloggingDistance());
    recordDto.setPloggingDate(recordEntity.getPloggingDate());
    recordDto.setPloggingTime(recordEntity.getPloggingTime());
    recordDto.setTrashCategory(recordEntity.getTrashCategory());
    recordDto.setRecordAttachImage(recordDto.getRecordAttachImage());

    return recordDto;

  }

}
