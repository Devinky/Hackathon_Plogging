package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingPartyPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PloggingPartyPhotoRepository extends JpaRepository<PloggingPartyPhotoEntity, Long> {
}
