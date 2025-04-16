package org.spring.hackathon.plogging.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.hackathon.common.service.ImageService;
import org.spring.hackathon.member.constructor.MemberConstructor;
import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.repository.MemberRepository;
import org.spring.hackathon.plogging.constructor.PloggingRecordConstructor;
import org.spring.hackathon.plogging.domain.PloggingLocationEntity;
import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.spring.hackathon.plogging.dto.PloggingRecordDto;
import org.spring.hackathon.plogging.dto.PloggingLocationDto;
import org.spring.hackathon.plogging.repository.PloggingLocationRepository;
import org.spring.hackathon.plogging.repository.PloggingRecordRepository;
import org.spring.hackathon.security.utils.JwtProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PloggingRecordService {

  private final JwtProvider jwtProvider;
  private final MemberRepository memberRepository;
  private final PloggingRecordRepository ploggingRecordRepository;
  private final PloggingLocationRepository ploggingLocationRepository;
  private final ImageService imageService;

  //플로깅 시작
  public Long ploggingStartDo(PloggingLocationDto location, String token, Long memberNo) {

    //전달받은 회원No에 해당하는 회원이 있다면 플로깅 기록 Save
    //회원 넘버가 정확히 넘어왔는지 확인
    Optional<MemberEntity> memberCheck = memberRepository.findById(memberNo);
    MemberEntity memberEntityGet = memberCheck.get();

    //현재 로그인한 회원과 운동을 시작하려고 하는 회원 정보값이 일치하는지 검증하기 위한 데이터
    String memberId = jwtProvider.getUserId(token.substring(7));

    if(!memberCheck.isPresent()) {
      throw new RuntimeException("정상적인 접근이 아닙니다. (회원 확인 불가!)");
    }

    if(!memberEntityGet.getMemberId().equals(memberId)) {
      throw new RuntimeException("정상적인 접근이 아닙니다. (로그인 정보 불일치!)");
    }

    //플로깅 테이블 생성
    PloggingRecordEntity ploggingRecord = PloggingRecordConstructor.ploggingStartTransfer(memberEntityGet);
    ploggingRecordRepository.save(ploggingRecord);

    //플로깅이 시작된 위치 좌표를 좌표 테이블에 저장
    PloggingLocationEntity locationInsert = PloggingRecordConstructor.ploggingLocationTransfer(location, ploggingRecord);
    ploggingLocationRepository.save(locationInsert);

    return ploggingRecord.getRecordNo();

  }

  //좌표 위치 업데이트
  @Transactional
  public PloggingLocationEntity ploggingLocationUpdate(PloggingLocationDto location, Long recordNo) {

    //운동이 진행되는 기록 레코드 조회
    Optional<PloggingRecordEntity> recordCheck = ploggingRecordRepository.findById(recordNo);

    //해당하는 레코드가 없으면 에러 메시지 출력
    if(!recordCheck.isPresent()) {
      throw new RuntimeException("정상적인 접근이 아닙니다. (기록 정보 조회 불가!)");
    }

    PloggingRecordEntity recordEntity = recordCheck.get();

    //넘겨받은 좌표 데이터를 대입
    PloggingLocationEntity locationUpdate = PloggingRecordConstructor.ploggingLocationTransfer(location, recordEntity);

    //이전 좌표와 현재 좌표를 대조해 계산하기 위해 좌표 리스트를 가져온다
    //동일한 플로깅 기록 넘버를 외래키로 가지고 있는 좌표를 리스트업
    List<PloggingLocationEntity> locationList = ploggingLocationRepository.findAllByPloggingRecordForeignKey(recordNo);
    
    //마지막 위치 좌표 Get
    PloggingLocationEntity lastLocation = locationList.get(locationList.size() - 1);

    //계산을 처리할 메서드로 값들을 보낸다
    double calcDistance = calculateDistance(lastLocation.getLatitude(), lastLocation.getLongitude(),
              locationUpdate.getLatitude(), locationUpdate.getLongitude());

    //플로깅 기록 테이블에 계산된 거리를 저장(현 시점에서 유저가 얼마나 이동했는지 총 거리)
    recordEntity.setPloggingDistance(recordEntity.getPloggingDistance() + calcDistance);

    //처리를 마친 데이터를 각 테이블에 저장
    ploggingRecordRepository.save(recordEntity);
    ploggingLocationRepository.save(locationUpdate);

    return locationUpdate;

  }
  
  //누적 거리 계산 메서드
  private double calculateDistance (double latBefore, double lonBefore, double latAfter, double lonAfter) {
    
    //Haversine 공식을 이용한 거리 계산
    //지구 반경(km)
    final double EARTH_RADIUS = 6371;

    double lat = Math.toRadians(latAfter - latBefore);
    double lon = Math.toRadians(lonAfter - lonBefore);

    double calc = Math.sin(lat / 2) * Math.sin(lat / 2)
            + Math.cos(Math.toRadians(latBefore)) * Math.cos(Math.toRadians(latAfter))
            * Math.sin(lon / 2) * Math.sin(lon / 2);

    double value = 2 * Math.atan2(Math.sqrt(calc), Math.sqrt(1 - calc));

    return EARTH_RADIUS * value;

  }

  //플로깅 종료
  @Transactional
  public void ploggingEndDo(PloggingRecordDto recordDto, PloggingLocationDto location, Long recordNo, MultipartFile image) throws IOException {

    //최종적으로 플로깅이 끝났을 때의 위치를 업데이트 처리
    PloggingLocationEntity locationProcessing = ploggingLocationUpdate(location, recordNo);

    //운동이 진행되는 기록 레코드 get
    Optional<PloggingRecordEntity> recordCheck = ploggingRecordRepository.findById(recordNo);
    PloggingRecordEntity finalRecordGet = recordCheck.get();

    //운동을 하는 회원 레코드 get
    Optional<MemberEntity> memberCheck = memberRepository.findById(finalRecordGet.getRecordJoinMember().getMemberNo());
    MemberEntity updateMemberGet = memberCheck.get();

    //운동 거리 계산, 1km를 기준으로 계산하여 소수점 둘째 자리 이후는 버린다
    double calcDistance = Math.floor(finalRecordGet.getPloggingDistance() * 100) / 100.0;
    log.info("이동 거리 : " + calcDistance);
    double totalDistance = updateMemberGet.getPloggingDistanceTotal() + calcDistance;
    log.info("총 거리 : " + totalDistance);

    //1km당 100포인트로 환산, 소숫점은 버린다
    int distanceCalcForPoint = (int) (finalRecordGet.getPloggingDistance() * 100);
    int totalPoint = distanceCalcForPoint + updateMemberGet.getPloggingPoint();

    //첨부된 이미지 처리
    if(!image.isEmpty()) {
      String identify = "plogging";
      imageService.imageSave(image, identify, recordNo);
    }

    //플로깅이 종료됐을 때 입력되는 정보들을 저장
    PloggingRecordEntity finalRecord = PloggingRecordConstructor.ploggingEndTransfer(recordDto, finalRecordGet, distanceCalcForPoint);
    MemberEntity updateMember = MemberConstructor.memberPloggingEndTransfer(updateMemberGet, totalDistance, totalPoint);

    ploggingRecordRepository.save(finalRecord);
    memberRepository.save(updateMember);

  }

}
