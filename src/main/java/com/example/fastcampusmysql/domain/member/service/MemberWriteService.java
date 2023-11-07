package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;
import com.example.fastcampusmysql.domain.member.entity.MemberNameHistoryJpaEntity;
import com.example.fastcampusmysql.domain.member.repository.MemberJpaRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberNameHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberWriteService {
    private final MemberJpaRepository memberJpaRepository;
    private final MemberNameHistoryJpaRepository memberNameHistoryJpaRepository;
//    private final MemberJdbcTemplateRepository memberJpaRepository;

    /**
     * 1. 회원정보 등록(이메일, 닉네임, 생년월일)
     *
     * @return MemberJpaEntity
     */
    public MemberJpaEntity register(RegisterMemberCommand registerMemberCommand) {
        // 회원 생성.
        MemberJpaEntity createMemberJpaEntity = MemberJpaEntity.of(registerMemberCommand);

        // 회원 저장.
        MemberJpaEntity memberJpaEntity = memberJpaRepository.save(createMemberJpaEntity);

        // 히스토리 내역 저장.
        saveNickname(memberJpaEntity);

        return memberJpaEntity;
    }

    /**
     * 회원 이름 변경.
     */
    public void changeNickname(Long memberId, String nickname) {
        MemberJpaEntity memberJpaEntity = memberJpaRepository.findById(memberId).orElseThrow();

        // 이름 변경.
        memberJpaEntity.changeNickname(nickname);

        // 히스토리 내역 저장.
        saveNickname(memberJpaEntity);
    }

    private void saveNickname(MemberJpaEntity memberJpaEntity) {
        MemberNameHistoryJpaEntity memberNameHistoryJpaEntity = MemberNameHistoryJpaEntity.builder()
                .member(memberJpaEntity)
                .name(memberJpaEntity.getNickname())
                .build();

        memberNameHistoryJpaRepository.save(memberNameHistoryJpaEntity);
    }

}
