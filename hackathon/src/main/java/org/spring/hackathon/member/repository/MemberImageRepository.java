package org.spring.hackathon.member.repository;

import org.spring.hackathon.member.domain.MemberImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberImageRepository extends JpaRepository<MemberImageEntity, Long> {
}
