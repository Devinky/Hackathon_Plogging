package org.spring.hackathon.plogging.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;
import org.spring.hackathon.plogging.service.PloggingViewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/plogging/view")
@RequiredArgsConstructor
public class PloggingViewController {

  private final PloggingViewService ploggingViewService;

  @GetMapping("/{memberNo}")
  public ResponseEntity<PloggingRecordDto> ploggingRecordView (@PathVariable Long memberNo) {

    

    return new ResponseEntity<>(new PloggingRecordDto(), HttpStatus.valueOf(200));
  }



}
