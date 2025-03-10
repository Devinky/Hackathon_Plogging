package org.spring.hackathon.plogging.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;
import org.spring.hackathon.plogging.dto.PloggingLocationDto;
import org.spring.hackathon.plogging.service.PloggingRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Log4j2
@RequestMapping("/plogging")
@RequiredArgsConstructor
public class PloggingController {

  //플로깅 기록 처리를 위한 컨트롤러
  private final PloggingRecordService ploggingService;

  //플로깅 시작 API
  @GetMapping("/start/{memberNo}")
  public ResponseEntity<Long> ploggingStart(@RequestBody PloggingLocationDto location,
                                            @RequestHeader("Authorization") String token, @PathVariable Long memberNo) {

    //플로깅을 시작한 지점의 위치를 보내고 기록이 저장되는 테이블의 id를 반환
    Long ploggingRecordNo = ploggingService.ploggingStartDo(location, token, memberNo);
    return ResponseEntity.ok().body(ploggingRecordNo);

  }

  //위치 좌표 체크, 업데이트 API
  @GetMapping("/location/{recordNo}")
  public ResponseEntity<String> ploggindLocationCheck(@RequestBody PloggingLocationDto location, @PathVariable Long recordNo) {

    ploggingService.ploggingLocationUpdate(location, recordNo);
    return ResponseEntity.ok("Location Check OK");
  }

  //플로깅 종료 API
  @GetMapping("/end/{recordNo}")
  public ResponseEntity<String> ploggingEnd(@RequestBody JsonNode ploggingObject, @PathVariable Long recordNo, MultipartFile ploggingImage)
          throws JsonProcessingException, IOException {

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      if(!ploggingObject.has("recordDto") || !ploggingObject.has("location")) {
        return ResponseEntity.badRequest().body("Data Format Invalid!");
      }

    PloggingRecordDto recordDto = objectMapper.treeToValue(ploggingObject.get("recordDto"), PloggingRecordDto.class);
    PloggingLocationDto location = objectMapper.treeToValue(ploggingObject.get("location"), PloggingLocationDto.class);

    ploggingService.ploggingEndDo(recordDto, location, recordNo, ploggingImage);

    return ResponseEntity.ok("Plogging Done");

    } catch(JsonProcessingException e) {
        return ResponseEntity.badRequest().body("Error : Parsing data -> " + e.getMessage());
    }

  }

}
