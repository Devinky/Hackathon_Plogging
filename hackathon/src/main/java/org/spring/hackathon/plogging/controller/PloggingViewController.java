package org.spring.hackathon.plogging.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;
import org.spring.hackathon.plogging.service.PloggingViewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/plogging/view")
@RequiredArgsConstructor
public class PloggingViewController {

  private final PloggingViewService ploggingViewService;

  @GetMapping("/{memberNo}")
  public ResponseEntity<List<PloggingRecordDto>> ploggingRecordView (@PathVariable Long memberNo) {

    List<PloggingRecordDto> getRecordList = ploggingViewService.recordThisMonthView(memberNo);
    return new ResponseEntity<>(getRecordList, HttpStatus.valueOf(200));

  }

  @GetMapping("/otherMonth/{memberNo}/{selectDate}")
  public ResponseEntity<List<PloggingRecordDto>> ploggingRecordOtherMonthView (@PathVariable Long memberNo, @PathVariable @DateTimeFormat(pattern = "yyyy-MM") YearMonth selectDate) {

    List<PloggingRecordDto> getRecordList = ploggingViewService.recordOtherMonthView(memberNo, selectDate);
    return new ResponseEntity<>(getRecordList, HttpStatus.valueOf(200));

  }

}
