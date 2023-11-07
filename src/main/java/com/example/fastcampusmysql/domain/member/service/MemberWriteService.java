package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;
import com.example.fastcampusmysql.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberWriteService {
    private final MemberJpaRepository memberJpaRepository;
//    private final MemberJdbcTemplateRepository memberJpaRepository;

    /**
     * 1. 회원정보 등록(이메일, 닉네임, 생년월일)
     *
     * @return MemberJpaEntity
     */
    public MemberJpaEntity register(RegisterMemberCommand registerMemberCommand) {
        // 회원 생성.
        MemberJpaEntity memberJpaEntity = MemberJpaEntity.of(registerMemberCommand);

        // 회원 저장.
        return memberJpaRepository.save(memberJpaEntity);
    }

    /**
     * 회원 이름 변경.
     */
    public void changeNickname(Long memberId, String nickname) {
        MemberJpaEntity memberJpaEntity = memberJpaRepository.findById(memberId).orElseThrow();
        memberJpaEntity.changeNickname(nickname);
    }

}
