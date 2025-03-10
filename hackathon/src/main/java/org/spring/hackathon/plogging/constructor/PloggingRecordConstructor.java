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
  public static PloggingRecordEntity ploggingEndTransfer(PloggingRecordDto recordDto, PloggingRecordEntity finalRecord) {

    finalRecord.setTrashCategory(recordDto.getTrashCategory());
    finalRecord.setPloggingTime(recordDto.getPloggingTime());
    finalRecord.setPloggingRecordAttachImage(recordDto.getRecordAttachImage());

    return finalRecord;

  }

}
