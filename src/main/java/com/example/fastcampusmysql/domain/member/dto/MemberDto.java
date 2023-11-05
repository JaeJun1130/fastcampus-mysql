package com.example.fastcampusmysql.domain.member.dto;

import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;

import java.time.LocalDate;

public record MemberDto(
        Long id,
        String email,
        String nickname,
        LocalDate birthday
) {
    public static MemberDto of(MemberJpaEntity memberJpaEntity) {
        return new MemberDto(
                memberJpaEntity.getId(),
                memberJpaEntity.getEmail(),
                memberJpaEntity.getNickname(),
                memberJpaEntity.getBirthday()
        );
    }
}
