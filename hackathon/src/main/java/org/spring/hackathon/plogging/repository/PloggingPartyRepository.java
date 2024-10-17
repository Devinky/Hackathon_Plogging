package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingPartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PloggingPartyRepository extends JpaRepository<PloggingPartyEntity, Long> {
}
