package org.spring.hackathon.plogging.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/plogging")
@RequiredArgsConstructor
public class PloggingController {

  //플로깅 기록 처리를 위한 컨트롤러
  //private final PloggingService ploggingService;

}
