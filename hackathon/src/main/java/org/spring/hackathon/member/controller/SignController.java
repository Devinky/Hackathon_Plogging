package org.spring.hackathon.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.member.dto.MemberDto;
import org.spring.hackathon.member.dto.MemberPhotoDto;
import org.spring.hackathon.member.service.MemberService;
import org.spring.hackathon.member.service.SignFileService;
import org.spring.hackathon.member.service.SignService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SignController {

  private final SignService signService;
  private final SignFileService signFileService;

  @PostMapping(value = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<String> signUp(@RequestPart MemberDto memberDto,
                                       @RequestPart(value = "file", required = false)MultipartFile memberFile) throws IOException {

    log.info("====================================회원가입 실행====================================");
    log.info("가입 정보 : " + memberDto);

    if (!memberFile.isEmpty()) {
      log.info("파일이 첨부됨.");
      log.info("첨부파일명 : " + memberFile.getOriginalFilename());
    } else {
      log.info("파일 없음.");
    }

    signService.signUp(memberDto);
    signFileService.fileSave(memberFile);

    return ResponseEntity.ok().body("회원가입 완료");

  }

  @PostMapping("/signin")
  public ResponseEntity<String> signIn(@RequestBody MemberDto dto){

    log.info("=====================================로그인 실행=====================================");

    String token = signService.signIn(dto.getMemberId(), dto.getMemberPassword());
    return ResponseEntity.ok().body(token);

  }

}
