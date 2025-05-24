package org.spring.hackathon.plogging.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PloggingRecordAndCoodinateDto {

  private PloggingRecordDto recordDto;

  private List<PloggingLocationDto> locationDto;

}
