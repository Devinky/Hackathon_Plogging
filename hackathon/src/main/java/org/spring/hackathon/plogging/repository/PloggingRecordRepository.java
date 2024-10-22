package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PloggingRecordRepository extends JpaRepository<PloggingRecordEntity, Long> {

}
