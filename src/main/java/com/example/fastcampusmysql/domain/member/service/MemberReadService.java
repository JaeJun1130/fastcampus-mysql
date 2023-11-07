package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;
import com.example.fastcampusmysql.domain.member.entity.MemberNameHistoryJpaEntity;
import com.example.fastcampusmysql.domain.member.repository.MemberJpaRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberNameHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberReadService {
    private final MemberJpaRepository memberJpaRepository;
    private final MemberNameHistoryJpaRepository memberNameHistoryJpaRepository;

    public MemberJpaEntity getMember(Long id) {
        return memberJpaRepository.findById(id).orElseThrow();
    }

    public List<MemberNameHistoryJpaEntity> getNicknameHistories(Long memberId) {
        MemberJpaEntity memberJpaEntity = memberJpaRepository.findById(memberId).orElseThrow();
        return memberNameHistoryJpaRepository.findByMember(memberJpaEntity);
    }
}
