package org.spring.hackathon.plogging.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;
import org.spring.hackathon.plogging.service.PloggingViewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/plogging/view")
@RequiredArgsConstructor
public class PloggingViewController {

  private final PloggingViewService ploggingViewService;

  @GetMapping("/{memberKey}")
  public ResponseEntity<List<PloggingRecordDto>> ploggingRecordView (@PathVariable Long memberKey, @RequestHeader("Authorization") String token) {

    List<PloggingRecordDto> getRecordList = ploggingViewService.recordThisMonthView(memberKey);
    return new ResponseEntity<>(getRecordList, HttpStatus.valueOf(200));

  }

  @GetMapping("/otherMonth/{memberKey}/{selectDate}")
  public ResponseEntity<List<PloggingRecordDto>> ploggingRecordOtherMonthView (@PathVariable Long memberKey,
                                                                               @PathVariable @DateTimeFormat(pattern = "yyyy-MM") YearMonth selectDate,
                                                                               @RequestHeader("Authorization") String token) {

    List<PloggingRecordDto> getRecordList = ploggingViewService.recordOtherMonthView(memberKey, selectDate);
    return new ResponseEntity<>(getRecordList, HttpStatus.valueOf(200));

  }

  @GetMapping("/{memberKey}")
  public ResponseEntity<String> ploggingDelete(@PathVariable Long memberKey, @RequestHeader("Authorization") String token) {

    ploggingViewService.ploggingRecordDelete(memberKey, token);
    return ResponseEntity.ok().body("삭제 완료");

  }

}
