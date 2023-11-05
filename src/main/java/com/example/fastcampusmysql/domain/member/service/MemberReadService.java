package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;
import com.example.fastcampusmysql.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberReadService {
    private final MemberJpaRepository memberJpaRepository;

    public MemberDto getMember(Long id) {
        MemberJpaEntity memberJpaEntity = memberJpaRepository.findById(id).orElseThrow();
        return MemberDto.of(memberJpaEntity);
    }
}
