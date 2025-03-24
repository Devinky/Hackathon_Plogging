package org.spring.hackathon.plogging.service;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.plogging.repository.PloggingRecordRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PloggingViewService {

  private final PloggingRecordRepository ploggingRecordRepository;

}
