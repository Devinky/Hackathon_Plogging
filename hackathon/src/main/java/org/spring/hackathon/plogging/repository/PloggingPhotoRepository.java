package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PloggingPhotoRepository extends JpaRepository<PloggingPhotoEntity, Long> {
}
