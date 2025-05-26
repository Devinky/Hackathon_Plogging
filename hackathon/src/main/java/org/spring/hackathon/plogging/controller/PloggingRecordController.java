package org.spring.hackathon.plogging.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
@RequestMapping("/plogging/do")
@RequiredArgsConstructor
public class PloggingRecordController {

  private final PloggingRecordService ploggingService;

  //플로깅 시작 API
  @GetMapping("/start/{memberKey}")
  public ResponseEntity<Long> ploggingStart(@RequestHeader("Authorization") String token,
                                            @RequestBody PloggingLocationDto location, @PathVariable Long memberKey) {

    //플로깅을 시작한 지점의 위치를 보내고 기록이 저장되는 테이블의 id를 반환
    Long ploggingRecordNo = ploggingService.ploggingStartDo(location, token, memberKey);
    return ResponseEntity.ok().body(ploggingRecordNo);

  }

  //위치 좌표 체크, 업데이트 API
  @GetMapping("/location/{recordKey}")
  public ResponseEntity<String> ploggindLocationCheck(@RequestBody PloggingLocationDto location, @PathVariable Long recordKey) {

    ploggingService.ploggingLocationUpdate(location, recordKey);
    return ResponseEntity.ok("Location Check OK");
  }

  //플로깅 종료 API
  @GetMapping("/end/{recordKey}")
  public ResponseEntity<String> ploggingEnd(@RequestPart ("record") String recordDtoJson, @RequestPart ("location") String locationJson,
                                            @RequestPart (value = "file", required = false) MultipartFile ploggingImage,
                                            @PathVariable Long recordKey) throws JsonProcessingException, IOException {

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      if(recordDtoJson.isEmpty() || locationJson.isEmpty()) {
        return ResponseEntity.badRequest().body("Data Invalid");
      }

    PloggingRecordDto recordDto = objectMapper.readValue(recordDtoJson, new TypeReference<PloggingRecordDto>() {});
    PloggingLocationDto location = objectMapper.readValue(locationJson, PloggingLocationDto.class);

    ploggingService.ploggingEndDo(recordDto, location, recordKey, ploggingImage);

    return ResponseEntity.ok("Plogging Done");

    } catch(JsonProcessingException e) {
        return ResponseEntity.badRequest().body("Error : Parsing data -> " + e.getMessage());
    }

  }

}
