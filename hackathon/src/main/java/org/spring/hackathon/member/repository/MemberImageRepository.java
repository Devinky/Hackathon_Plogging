package org.spring.hackathon.member.repository;

import org.spring.hackathon.member.domain.MemberImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberImageRepository extends JpaRepository<MemberImageEntity, Long> {

  @Query(value = "SELECT file_path FROM member_image WHERE member_fk = :memberNo", nativeQuery = true)
  String findFilePathWithMemberFk(Long memberNo);

}
