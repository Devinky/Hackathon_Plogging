package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PloggingImageRepository extends JpaRepository<PloggingImageEntity, Long> {

  @Query(value = "SELECT * FROM plogging_image WHERE plogging_record_fk = :recordKey", nativeQuery = true)
  PloggingImageEntity findByRecordForeignKey(Long recordKey);

}
