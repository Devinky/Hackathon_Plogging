package org.spring.hackathon.users.repository;

import org.spring.hackathon.users.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  //회원가입 시 ID 중복 체크
  Optional<MemberEntity> findByMemberId(String memberId);


}
