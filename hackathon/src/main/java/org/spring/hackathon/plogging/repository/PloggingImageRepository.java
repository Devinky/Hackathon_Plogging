package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PloggingImageRepository extends JpaRepository<PloggingImageEntity, Long> {
}
