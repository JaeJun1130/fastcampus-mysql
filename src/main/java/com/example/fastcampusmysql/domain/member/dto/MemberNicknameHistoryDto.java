package com.example.fastcampusmysql.domain.member.dto;

import com.example.fastcampusmysql.domain.member.entity.MemberNameHistoryJpaEntity;

import java.time.LocalDateTime;

public record MemberNicknameHistoryDto(
        Long id,
        Long memberId,
        String nickname,
        LocalDateTime createdAt
) {
    public static MemberNicknameHistoryDto of(MemberNameHistoryJpaEntity memberNameHistoryJpaEntity) {
        return new MemberNicknameHistoryDto(
                memberNameHistoryJpaEntity.getId(),
                memberNameHistoryJpaEntity.getMember().getId(),
                memberNameHistoryJpaEntity.getName(),
                memberNameHistoryJpaEntity.getCreatedAt()
        );
    }
}
