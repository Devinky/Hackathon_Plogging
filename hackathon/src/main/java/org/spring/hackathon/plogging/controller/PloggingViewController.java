package org.spring.hackathon.plogging.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;
import org.spring.hackathon.plogging.dto.PloggingRecordAndCoordinateDto;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;
import org.spring.hackathon.plogging.service.PloggingViewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/plogging/view")
@RequiredArgsConstructor
public class PloggingViewController {

  private final PloggingViewService ploggingViewService;

  @GetMapping("/{memberKey}")
  public ResponseEntity<List<PloggingRecordDto>> ploggingRecordView
      (@RequestHeader("Authorization") String token, @PathVariable Long memberKey) {

    List<PloggingRecordDto> getRecordList = ploggingViewService.recordThisMonthView(token, memberKey);
    return new ResponseEntity<>(getRecordList, HttpStatus.valueOf(200));

  }

  @GetMapping("/otherMonth/{memberKey}/{selectDate}")
  public ResponseEntity<List<PloggingRecordDto>> ploggingRecordOtherMonthView
      (@RequestHeader("Authorization") String token,
       @PathVariable Long memberKey,
       @PathVariable @DateTimeFormat(pattern = "yyyy-MM") YearMonth selectDate) {

    List<PloggingRecordDto> getRecordList = ploggingViewService.recordOtherMonthView(token, memberKey, selectDate);
    return new ResponseEntity<>(getRecordList, HttpStatus.valueOf(200));

  }

  @GetMapping("/detail/{memberKey}/{recordKey}")
  public ResponseEntity<PloggingRecordAndCoordinateDto> ploggingRecordDetailView
      (@RequestHeader("Authorization") String token,
       @PathVariable Long memberKey, @PathVariable Long recordKey) {

    PloggingRecordAndCoordinateDto getRecordDetail = ploggingViewService.recordDetailView(token, memberKey, recordKey);
    return new ResponseEntity<>(getRecordDetail, HttpStatus.valueOf(200));

  }

  @GetMapping("/delete/{memberKey}/{recordKey}")
  public ResponseEntity<String> ploggingDelete
      (@RequestHeader("Authorization") String token, @PathVariable Long memberKey, @PathVariable Long recordKey) {

    ploggingViewService.ploggingRecordDelete(token, memberKey, recordKey);
    return ResponseEntity.ok().body("삭제 완료");

  }

}
