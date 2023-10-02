package org.spring.hackathon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.spring.hackathon.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class MemberControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  MemberService memberService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @DisplayName("회원가입 성공")
  void join() throws Exception {

  }

}
