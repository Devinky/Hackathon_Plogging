package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingPartyImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PloggingPartyImageRepository extends JpaRepository<PloggingPartyImageEntity, Long> {
}
