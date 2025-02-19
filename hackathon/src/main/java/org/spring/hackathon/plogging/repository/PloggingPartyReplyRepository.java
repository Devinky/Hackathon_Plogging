package org.spring.hackathon.plogging.repository;

import org.spring.hackathon.plogging.domain.PloggingPartyReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PloggingPartyReplyRepository extends JpaRepository<PloggingPartyReplyEntity, Long> {
}
