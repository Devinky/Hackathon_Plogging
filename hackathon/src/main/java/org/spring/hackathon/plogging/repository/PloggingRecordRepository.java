package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PloggingRecordRepository extends JpaRepository<PloggingRecordEntity, Long> {

  @Query(value = "SELECT * FROM plogging_record WHERE member_record_fk = :memberNo AND plogging_date BETWEEN date(:startDate) AND date(:lastDate)", nativeQuery = true)
  List<PloggingRecordEntity> findByIdAndMonth(Long memberNo, LocalDate startDate, LocalDate lastDate);

//  @Query(value = "SELECT * FROM plogging_record WHERE member_record_fk = :memberNo AND plogging_date BETWEEN date(:startDate) AND date(:lastDate)", nativeQuery = true)
//  List<PloggingRecordEntity> findByIdWithSelectMonth(Long memberNo, LocalDate startDate, LocalDate lastDate);

}
