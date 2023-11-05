package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.MemberJpaEntity;
import com.example.fastcampusmysql.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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

}
