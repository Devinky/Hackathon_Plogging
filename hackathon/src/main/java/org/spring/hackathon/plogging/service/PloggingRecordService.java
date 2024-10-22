package org.spring.hackathon.plogging.service;

import lombok.RequiredArgsConstructor;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.repository.MemberRepository;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.spring.hackathon.plogging.dto.PloggingLocationDto;
import org.spring.hackathon.plogging.repository.PloggingLocationRepository;
import org.spring.hackathon.plogging.repository.PloggingRecordRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PloggingRecordService {

  private final MemberRepository memberRepository;
  private final PloggingRecordRepository ploggingRecordRepository;
  private final PloggingLocationRepository ploggingLocationRepository;

  public Long ploggingStartDo(PloggingLocationDto location, Long memberNo) {

    //전달받은 회원No에 해당하는 회원이 있다면 플로깅 기록 Save
    Optional<MemberEntity> memberCheck = memberRepository.findById(memberNo);
    if(memberCheck.isPresent()) {

      MemberEntity memberEntity = memberCheck.get();

      PloggingRecordEntity ploggingRecord = new PloggingRecordEntity();

      ploggingRecord.setPloggingDate(LocalDate.now());
      ploggingRecord.setPloggingRecordAttachPhoto(0);
      ploggingRecord.setRecordJoinMember(memberEntity);

      ploggingRecordRepository.save(ploggingRecord);

    } else { new IllegalArgumentException();}

      return ploggingRecord.getRecordNo();

  }

  public void ploggingLocationUpdate(PloggingLocationDto location, Long ploggingRecordNo) {
  }

  @Transactional
  public void ploggingEndDo(Long ploggingRecordNo) {
  }
}
