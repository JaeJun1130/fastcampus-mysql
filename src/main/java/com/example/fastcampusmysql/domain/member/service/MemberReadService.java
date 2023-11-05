package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;
import com.example.fastcampusmysql.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberReadService {
    private final MemberJpaRepository memberJpaRepository;

    public MemberJpaEntity getMember(Long id) {
        return memberJpaRepository.findById(id).orElseThrow();
    }
}
