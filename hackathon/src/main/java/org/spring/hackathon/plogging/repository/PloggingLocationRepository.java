package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PloggingLocationRepository extends JpaRepository<PloggingLocationEntity, Long> {

  //특정 운동 기록에 속한 모든 위치 좌표를 조회
  @Query(value = "SELECT * FROM plogging_location WHERE record_location_fk = :recordKey", nativeQuery = true)
  List<PloggingLocationEntity> findAllByPloggingRecordForeignKey(@Param("recordKey") Long ploggingRecordId);

}
