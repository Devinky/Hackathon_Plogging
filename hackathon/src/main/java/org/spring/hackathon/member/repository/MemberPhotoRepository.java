package org.spring.hackathon.member.repository;

import org.spring.hackathon.member.domain.MemberPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPhotoRepository extends JpaRepository<MemberPhotoEntity, Long> {
}
