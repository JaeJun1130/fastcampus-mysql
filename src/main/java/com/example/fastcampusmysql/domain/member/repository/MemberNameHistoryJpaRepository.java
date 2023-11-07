package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;
import com.example.fastcampusmysql.domain.member.entity.MemberNameHistoryJpaEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberNameHistoryJpaRepository extends JpaRepository<MemberNameHistoryJpaEntity, Long> {
    @Query("SELECT mnh FROM MemberNameHistoryJpaEntity mnh WHERE mnh.member = :member")
    List<MemberNameHistoryJpaEntity> findByMember(@Param("member") MemberJpaEntity memberJpaEntity);
}
