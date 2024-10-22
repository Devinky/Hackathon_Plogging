package org.spring.hackathon.plogging.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.plogging.dto.PloggingLocationDto;
import org.spring.hackathon.plogging.service.PloggingRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/plogging")
@RequiredArgsConstructor
public class PloggingController {

  //플로깅 기록 처리를 위한 컨트롤러
  private final PloggingRecordService ploggingService;

  //플로깅 시작 API
  @GetMapping("/start/{memberNo}")
  public ResponseEntity<Long> ploggingStart(@RequestBody PloggingLocationDto location, @PathVariable Long memberNo) {

    //플로깅을 시작한 지점의 위치를 보내고 기록이 저장되는 테이블의 id를 반환하는 로직
    Long ploggingRecordNo = ploggingService.ploggingStartDo(location, memberNo);

    return ResponseEntity.ok().body(ploggingRecordNo);

  }

  //위치 좌표 체크, 업데이트 API
  @PostMapping("/location")
  public ResponseEntity<String> ploggindLocationCheck(@RequestBody PloggingLocationDto location, @RequestParam Long ploggingRecordNo) {

    ploggingService.ploggingLocationUpdate(location, ploggingRecordNo);
    return ResponseEntity.ok("Location Check OK");
  }

  @GetMapping("/end")
  public ResponseEntity<String> ploggingEnd(@RequestParam Long ploggingRecordNo) {

    ploggingService.ploggingEndDo(ploggingRecordNo);
    return ResponseEntity.ok("");
  }

}
